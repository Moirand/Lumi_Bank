package com.example.feature_transfer.ui.fellowBankAccountInputScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.common.makeToast
import com.example.core.Resource
import com.example.core.model.response.AccountsResponseCore
import com.example.feature_transfer.databinding.FragmentAccountBottomSheetDialogBinding
import com.example.feature_transfer.viewModel.TransferViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("SetTextI18n")
class AccountBottomSheetDialogFragment(
    private val accountResponseCore: AccountsResponseCore
) : BottomSheetDialogFragment() {
    private var _binding: FragmentAccountBottomSheetDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<TransferViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentAccountBottomSheetDialogBinding.inflate(layoutInflater).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTutup.setOnClickListener {
            this.dismiss()
        }

        binding.tvNamaPenerima.text = accountResponseCore.userName
        binding.tvBankRekeningPenerima.text =
            "${accountResponseCore.bankName} - ${accountResponseCore.accountNumber}"
        binding.btnLanjut.setOnClickListener {
            findNavController().navigate(
                FellowBankAccountInputFragmentDirections.actionFellowBankAccountInputFragmentToTransferInputFragment(
                    accountDestinationName = accountResponseCore.userName!!,
                    accountDestinationNo = accountResponseCore.accountNumber!!,
                    accountDestinationBank = accountResponseCore.bankName!!
                )
            )
            this.dismiss()
        }
        binding.btnSimpanPenerima.setOnClickListener {
            viewModel.saveAccount(
                accountNumber = accountResponseCore.accountNumber!!,
                bankName = accountResponseCore.bankName!!
            )
        }

        viewModel.accountSaveResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {}
                is Resource.Error -> {
                    makeToast(requireContext(), response.message)
                }

                is Resource.Success -> {
                    findNavController().navigate(
                        FellowBankAccountInputFragmentDirections.actionFellowBankAccountInputFragmentToTransferInputFragment(
                            accountDestinationName = accountResponseCore.userName!!,
                            accountDestinationNo = accountResponseCore.accountNumber!!,
                            accountDestinationBank = accountResponseCore.bankName!!
                        )
                    )
                    this.dismiss()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}