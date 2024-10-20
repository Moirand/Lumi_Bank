package com.example.feature_transfer.ui.savedAccountScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.makeToast
import com.example.core.Resource
import com.example.feature_transfer.R
import com.example.feature_transfer.databinding.FragmentSavedAccountBinding
import com.example.feature_transfer.viewModel.TransferViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedAccountFragment : Fragment() {
    private var _binding: FragmentSavedAccountBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<TransferViewModel>()
    private lateinit var sectionItemListAdapter: SectionItemListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSavedAccountBinding.inflate(layoutInflater).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSavedAccounts()

        binding.btnKembali.setOnClickListener { findNavController().popBackStack() }
        binding.btnSesamaBank.setOnClickListener { }
        binding.btnAntarBank.setOnClickListener { }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true
            override fun onQueryTextChange(query: String?): Boolean {
                sectionItemListAdapter.search(query)
                return true
            }
        })

        binding.btnTambahPenerimaBaru.setOnClickListener {
            findNavController().navigate(R.id.action_savedAccountFragment_to_fellowBankAccountInputFragment)
        }

        viewModel.savedAccountsData.observe(viewLifecycleOwner) { savedAccounts ->
            when (savedAccounts) {
                is Resource.Loading -> handleLoading(true)
                is Resource.Error -> {
                    handleLoading(false)
                    makeToast(requireContext(), savedAccounts.message)
                }

                is Resource.Success -> {
                    handleLoading(false)
                    val sortedList = savedAccounts.data?.data?.sortedBy { it?.name }
                    sortedList?.let {
                        sectionItemListAdapter = SectionItemListAdapter(it)
                        with(binding.rvAkunTersimpan) {
                            adapter = sectionItemListAdapter
                            layoutManager = LinearLayoutManager(context)
                        }
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