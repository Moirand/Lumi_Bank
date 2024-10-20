package com.example.feature_transfer.ui.fellowBankAccountInputScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.common.makeToast
import com.example.core.Resource
import com.example.feature_transfer.databinding.FragmentFellowBankAccountInputBinding
import com.example.feature_transfer.viewModel.TransferViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FellowBankAccountInputFragment : Fragment() {
    private var _binding: FragmentFellowBankAccountInputBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<TransferViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentFellowBankAccountInputBinding.inflate(layoutInflater).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnKembali.setOnClickListener {
            findNavController().popBackStack()
        }

        with(binding.spinnerNamaBank) {
            val spinnerAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                com.example.common.R.array.bank_list,
                android.R.layout.simple_spinner_item
            )
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            adapter = spinnerAdapter
        }

        binding.btnCekRekening.setOnClickListener {
            viewModel.getAccountByAccountNumber(
                binding.tiedtNomorRekening.text.toString(),
                binding.spinnerNamaBank.selectedItem.toString()
            )
        }

        viewModel.checkedAccount.observe(viewLifecycleOwner) { accounts ->
            when (accounts) {
                is Resource.Loading -> handleLoading(true)
                is Resource.Error -> {
                    handleLoading(false)
                    makeToast(requireContext(), accounts.message)
                }

                is Resource.Success -> {
                    val searchedAccount = accounts.data?.find {
                        it.accountNumber == binding.tiedtNomorRekening.text.toString() &&
                                it.bankName == binding.spinnerNamaBank.selectedItem.toString()
                    }
                    if (searchedAccount != null) {
                        AccountBottomSheetDialogFragment(searchedAccount).show(
                            requireActivity().supportFragmentManager,
                            ""
                        )
                    } else {
                        makeToast(requireContext(), "Account not found")
                    }
                }
            }
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}