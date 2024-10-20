package com.example.feature_transfer.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.common.makeToast
import com.example.common.toRupiah
import com.example.core.Resource
import com.example.feature_transfer.databinding.FragmentTransferSuccessBinding
import com.example.feature_transfer.viewModel.TransferViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@SuppressLint("SetTextI18n")
@RequiresApi(Build.VERSION_CODES.O)
class TransferSuccessFragment : Fragment() {
    private var _binding: FragmentTransferSuccessBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<TransferViewModel>()
    private val args by navArgs<TransferSuccessFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentTransferSuccessBinding.inflate(layoutInflater).also {
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
                        tvNamaPenerima.text = mutationData.data?.data?.usernameTo
                        tvBankRekeningPenerima.text =
                            "Lumi Bank - " + mutationData.data?.data?.accountTo
                        tvNominal.text = mutationData.data?.data?.amount.toString().toRupiah()
                        tvTanggal.text = formatDateTime(mutationData.data?.data?.datetime)
                        tvWaktu.text = formatHourTime(mutationData.data?.data?.datetime)
                    }
                }
            }
        }

        binding.btnBagikan.setOnClickListener {

        }

        binding.btnDetail.setOnClickListener {
            findNavController().navigate(
                TransferSuccessFragmentDirections.actionTransferSuccessFragmentToTransferDetailFragment(
                    transferId = args.transferId
                )
            )
        }

        binding.btnSelesai.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun formatDateTime(datetime: String?): String {
        val formatterWith6Digits = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
        val formatterWith5Digits = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSS")

        val localDateTime = try {
            LocalDateTime.parse(datetime, formatterWith6Digits)
        } catch (e: Exception) {
            LocalDateTime.parse(datetime, formatterWith5Digits)
        }

        val outputFormatter =
            DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", Locale("id", "ID"))
        return localDateTime.format(outputFormatter)
    }

    private fun formatHourTime(datetime: String?): String {
        val formatterWith6Digits = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
        val formatterWith5Digits = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSS")

        val localDateTime = try {
            LocalDateTime.parse(datetime, formatterWith6Digits)
        } catch (e: Exception) {
            LocalDateTime.parse(datetime, formatterWith5Digits)
        }

        val dateTimePlus7Hours = localDateTime.plusHours(7)
        val outputFormatter = DateTimeFormatter.ofPattern("HH.mm.ss", Locale("id", "ID"))
        return dateTimePlus7Hours.format(outputFormatter)
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}