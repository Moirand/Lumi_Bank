package com.example.feature_dashboard.ui.qrisScreen

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common.hideAccountNumber
import com.example.common.hideBalance
import com.example.common.makeToast
import com.example.common.toRupiah
import com.example.core.Resource
import com.example.feature_dashboard.databinding.FragmentQrCodeShowBottomSheetDialogBinding
import com.example.feature_dashboard.viewModel.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.common.CharacterSetECI
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.EnumMap

class QrCodeShowBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentQrCodeShowBottomSheetDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentQrCodeShowBottomSheetDialogBinding.inflate(layoutInflater).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.localUserData.observe(viewLifecycleOwner) { userData ->
            viewModel.getBalance(userData.accountNumber)
            binding.tvRekening.text = userData.accountNumber.hideAccountNumber()

            val mQRBitmap = generateQR("${userData.accountNumber} ${userData.name}")
            if (mQRBitmap != null) {
                binding.imgQrCode.setImageBitmap(mQRBitmap)
            } else {
                makeToast(requireContext(), "Gagal membuat QR Code")
            }
        }

        viewModel.balanceGetResult.observe(viewLifecycleOwner) { balance ->
            when (balance) {
                is Resource.Loading -> {}
                is Resource.Error -> {
                    makeToast(requireContext(), balance.message)
                }

                is Resource.Success -> {
                    binding.toggleSaldo.apply {
                        text = balance.data?.data.toString().hideBalance()
                        textOn = balance.data?.data.toString().toRupiah()
                        textOff = balance.data?.data.toString().hideBalance()
                    }
                }
            }
        }
    }

    private fun generateQR(value: String): Bitmap? {
        val bitMatrix: BitMatrix
        try {
            val hints = EnumMap<EncodeHintType, Any>(EncodeHintType::class.java)
            hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H
            hints[EncodeHintType.CHARACTER_SET] = CharacterSetECI.UTF8

            bitMatrix = MultiFormatWriter().encode(
                value,
                BarcodeFormat.QR_CODE,
                500,
                500,
                hints
            )
        } catch (illegalArgumentException: IllegalArgumentException) {
            return null
        }

        val bitMatrixWidth = bitMatrix.width

        val bitMatrixHeight = bitMatrix.height

        val pixels = IntArray(bitMatrixWidth * bitMatrixHeight)

        for (y in 0 until bitMatrixHeight) {
            val offset = y * bitMatrixWidth

            for (x in 0 until bitMatrixWidth) {

                pixels[offset + x] = if (bitMatrix.get(x, y))
                    Color.BLACK
                else
                    Color.WHITE
            }
        }
        val bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444)
        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight)

        return bitmap
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}