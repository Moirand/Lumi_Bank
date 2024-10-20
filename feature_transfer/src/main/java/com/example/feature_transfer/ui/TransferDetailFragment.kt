package com.example.feature_transfer.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateUtils.formatDateTime
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.common.makeToast
import com.example.common.toRupiah
import com.example.core.Resource
import com.example.feature_transfer.R
import com.example.feature_transfer.databinding.FragmentTransferDetailBinding
import com.example.feature_transfer.databinding.FragmentTransferSuccessBinding
import com.example.feature_transfer.viewModel.TransferViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("SetTextI18n")
class TransferDetailFragment : Fragment() {
    private var _binding: FragmentTransferDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<TransferViewModel>()
    private val args by navArgs<TransferDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentTransferDetailBinding.inflate(layoutInflater).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMutationById(args.transferId)

        viewModel.mutationData.observe(viewLifecycleOwner) { mutationData ->
            when (mutationData) {
                is Resource.Loading -> handleLoading(true)
                is Resource.Error -> {
                    handleLoading(false)
                    makeToast(requireContext(), mutationData.message)
                }

                is Resource.Success -> {
                    handleLoading(false)
                    with(binding) {
                        tvTanggalWaktu.text = mutationData.data?.data?.datetime
                        tvNomorTransfer.text = "No Ref. " + mutationData.data?.data?.id
                        tvNamaPenerima.text = mutationData.data?.data?.usernameTo
                        tvBankRekeningPenerima.text =
                            "Lumi Bank - " + mutationData.data?.data?.accountTo
                        tvTotal.text = mutationData.data?.data?.amount.toString().toRupiah()
                        tvNamaPengirim.text = mutationData.data?.data?.usernameFrom
                        tvBankRekeningPengirim.text =
                            "Lumi Bank - " + mutationData.data?.data?.accountFrom
                    }
                }
            }
        }

        binding.btnTutup.setOnClickListener {
            findNavController().popBackStack()
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