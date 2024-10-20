package com.example.feature_mutasi.ui.mutasiScreen

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.makeToast
import com.example.core.Resource
import com.example.feature_mutasi.databinding.FragmentMutasiBinding
import com.example.feature_mutasi.viewModel.MutationViewmodel
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Date
import java.util.Locale

class MutationFragment : Fragment() {
    private var _binding: FragmentMutasiBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<MutationViewmodel>()

    private var startDate: String? = null
    private var endDate: String? = null
    private var transactionType: String = "Semua"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMutasiBinding.inflate(layoutInflater).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnKembali.setOnClickListener { findNavController().popBackStack() }

        binding.tilTanggalAwal.setEndIconOnClickListener {
            showDateEndPickerDialog { selectedStartDate ->
                binding.tiedtTanggalAwal.setText(selectedStartDate)
                startDate = selectedStartDate

                if (endDate != null) {
                    viewModel.getMutations(startDate, endDate)
                }
            }
        }

        binding.tilTanggalAkhir.setEndIconOnClickListener {
            showDateEndPickerDialog { selectedEndDate ->
                binding.tiedtTanggalAkhir.setText(selectedEndDate)
                endDate = selectedEndDate

                if (startDate != null) {
                    viewModel.getMutations(startDate, endDate)
                }
            }
        }

        val transactionTypeList = listOf("Semua", "Uang Masuk", "Uang Keluar")
        val spinnerAdapter =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                transactionTypeList
            )
        with(binding.spinner) {
            adapter = spinnerAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> transactionType = "Semua"
                        1 -> transactionType = "Uang Masuk"
                        2 -> transactionType = "Uang Keluar"
                    }

                    viewModel.getMutations(startDate, endDate)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }

        viewModel.mutationData.observe(viewLifecycleOwner) { mutationData ->
            when (mutationData) {
                is Resource.Loading -> handleLoading(true)
                is Resource.Error -> {
                    handleLoading(false)
                    makeToast(requireContext(), mutationData.message)
                }

                is Resource.Success -> {
                    handleLoading(false)
                    mutationData.data?.data?.let {
                        val filteredMutation =
                            viewModel.filterMutationByType(
                                it.asReversed(),
                                transactionType,
                                viewModel.accountNumbers
                            )
                        val groupedMutations = viewModel.groupMutationsByDate(filteredMutation)
                        with(binding.rvMutasi) {
                            adapter = DateItemListAdapter(
                                groupedMutations,
                                viewModel.accountNumbers!!
                            )
                            layoutManager = LinearLayoutManager(requireContext())
                        }
                    }
                }
            }
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showDateEndPickerDialog(onDateSelected: (String) -> Unit) {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        datePicker.addOnPositiveButtonClickListener { selection ->
            val selectedDateMillis = selection ?: return@addOnPositiveButtonClickListener
            val selectedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .format(Date(selectedDateMillis))

            onDateSelected(selectedDate)
        }
        datePicker.show(parentFragmentManager, "DATE_PICKER_TAG")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}