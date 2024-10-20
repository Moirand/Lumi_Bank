package com.example.feature_transfer.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.common.makeToast
import com.example.common.toRupiah
import com.example.core.Resource
import com.example.feature_transfer.databinding.FragmentTransferInputBinding
import com.example.feature_transfer.viewModel.TransferViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("SetTextI18n")
class TransferInputFragment : Fragment() {
    private var _binding: FragmentTransferInputBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<TransferViewModel>()
    private val args by navArgs<TransferInputFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentTransferInputBinding.inflate(layoutInflater).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBalance()

        binding.btnKembali.setOnClickListener { findNavController().popBackStack() }

        binding.tvNamaPenerima.text = args.accountDestinationName
        binding.tvBankRekeningPenerima.text = "${args.accountDestinationBank} - ${args.accountDestinationNo}"

        binding.btnLanjut.setOnClickListener {
            if (isAmountValid(binding.tiedtNominal.text.toString().toIntOrNull())) {
                findNavController().navigate(
                    TransferInputFragmentDirections.actionTransferInputFragmentToTransferPinInputFragment(
                        accountDestinationName = args.accountDestinationName,
                        accountDestinationNo = args.accountDestinationNo,
                        accountDestinationBank = args.accountDestinationBank,
                        transferAmount = binding.tiedtNominal.text.toString().toInt(),
                        transferDescription = binding.tiedtDeskripsi.text.toString()
                    )
                )
            }
        }

        viewModel.balanceData.observe(viewLifecycleOwner) { balance ->
            when (balance) {
                is Resource.Loading -> handleLoading(true)
                is Resource.Error -> {
                    handleLoading(false)
                    makeToast(requireContext(), balance.message)
                }

                is Resource.Success -> {
                    handleLoading(false)
                    binding.tvSaldoPengirim.text = balance.data?.data.toString().toRupiah()
                }
            }
        }
    }

    private fun isAmountValid(amount: Int?): Boolean =
        when {
            amount == null || amount < 1000 -> {
                makeToast(requireContext(), "Nominal harus di atas Rp. 1.000!")
                false
            }

            amount > 5000000 -> {
                makeToast(requireContext(), "Nominal maksimal Rp. 25.000.000!")
                false
            }

            else -> true
        }

    private fun handleLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}