package com.example.feature_transfer.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.common.makeToast
import com.example.core.Resource
import com.example.feature_transfer.databinding.FragmentTransferPinInputBinding
import com.example.feature_transfer.viewModel.TransferViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@RequiresApi(Build.VERSION_CODES.O)
class TransferPinInputFragment : Fragment() {
    private var _binding: FragmentTransferPinInputBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<TransferViewModel>()

    private val numberList = mutableListOf<Char>()
    private val args by navArgs<TransferPinInputFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentTransferPinInputBinding.inflate(layoutInflater).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpNumberButtons()

        viewModel.transferResult.observe(viewLifecycleOwner) { transferResult ->
            when (transferResult) {
                is Resource.Loading -> handleLoading(true)
                is Resource.Error -> {
                    handleLoading(false)
                    makeToast(requireContext(), transferResult.message)
                }

                is Resource.Success -> {
                    handleLoading(false)
                    findNavController().navigate(
                        TransferPinInputFragmentDirections.actionTransferPinInputFragmentToTransferSuccessFragment(
                            transferId = transferResult.data?.data?.id!!
                        )
                    )
                }
            }
        }
    }

    private fun setUpNumberButtons() {
        val numberButtons = listOf(
            com.example.common.R.id.btn_num_0,
            com.example.common.R.id.btn_num_1,
            com.example.common.R.id.btn_num_2,
            com.example.common.R.id.btn_num_3,
            com.example.common.R.id.btn_num_4,
            com.example.common.R.id.btn_num_5,
            com.example.common.R.id.btn_num_6,
            com.example.common.R.id.btn_num_7,
            com.example.common.R.id.btn_num_8,
            com.example.common.R.id.btn_num_9,
        )

        numberButtons.forEachIndexed { index, buttonId ->
            binding.nomorPin.findViewById<TextView>(buttonId).setOnClickListener {
                addNumberToList(index.toString()[0])
            }
        }

        binding.nomorPin.findViewById<TextView>(com.example.common.R.id.btn_hapus)
            .setOnClickListener {
                if (numberList.isNotEmpty()) {
                    numberList.removeAt(numberList.lastIndex)
                    updatePinDisplay()
                }
            }
    }

    private fun addNumberToList(number: Char) {
        if (numberList.size < 6) {
            numberList.add(number)
            updatePinDisplay()
            if (numberList.size == 6) {
                viewModel.transfer(
                    destinationNo = args.accountDestinationNo,
                    amount = args.transferAmount,
                    destinationBank = args.accountDestinationBank,
                    description = args.transferDescription,
                    pin = numberList.joinToString("")
                )
            }
        }
    }

    private fun updatePinDisplay() {
        val pinInputs = listOf(
            com.example.common.R.id.tv_pin_input1,
            com.example.common.R.id.tv_pin_input2,
            com.example.common.R.id.tv_pin_input3,
            com.example.common.R.id.tv_pin_input4,
            com.example.common.R.id.tv_pin_input5,
            com.example.common.R.id.tv_pin_input6,
        )

        pinInputs.forEachIndexed { index, inputId ->
            val background = if (index < numberList.size) {
                com.example.common.R.drawable.pin_bullet_filled
            } else {
                com.example.common.R.drawable.pin_bullet
            }
            binding.masukkanPin.findViewById<TextView>(inputId).setBackgroundResource(background)
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