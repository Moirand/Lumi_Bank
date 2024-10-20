package com.example.feature_dashboard.ui.qrisScreen

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.example.common.makeToast
import com.example.feature_dashboard.databinding.FragmentQrCodeScanBinding
import com.google.zxing.BinaryBitmap
import com.google.zxing.LuminanceSource
import com.google.zxing.MultiFormatReader
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.Reader
import com.google.zxing.Result
import com.google.zxing.common.HybridBinarizer
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class QrCodeScanFragment : Fragment() {
    private var _binding: FragmentQrCodeScanBinding? = null
    private val binding get() = _binding!!

    private lateinit var codeScanner: CodeScanner

    private val resultCameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                makeToast(requireContext(), "Izin kamera diberikan")
                codeScanner.startPreview()
            } else {
                makeToast(requireContext(), "Izin kamera ditolak")
            }
        }

    private val resultGalleryPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageUri = result.data!!.data!!
                val imagePath = convertMediaUriToPath(imageUri)
                val imgFile = File(imagePath)

                scanQRFromImage(imgFile)
            } else {
                makeToast(requireContext(), "Izin Ditolak")
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentQrCodeScanBinding.inflate(layoutInflater).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        codeScanner = CodeScanner(requireActivity(), binding.scannerView)
        codeScanner.decodeCallback = DecodeCallback { scanResult ->
            requireActivity().runOnUiThread {
                val accountDestinationName = scanResult.text.split(" ")[1]
                val accountDestinationNo = scanResult.text.split(" ")[0]
                makeToast(requireContext(), "$accountDestinationNo $accountDestinationName")
            }
        }
        checkCameraPermissionAndOpenCamera()

        binding.btnTerimaPembayaran.setOnClickListener {
            QrCodeShowBottomSheetDialogFragment().show(requireActivity().supportFragmentManager, "")
        }

        binding.btnTransfer.setOnClickListener {
            QrCodeShowBottomSheetDialogFragment().show(requireActivity().supportFragmentManager, "")
        }

        binding.btnBukaGallery.setOnClickListener {
            val galleryIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            resultGalleryPermissionLauncher.launch(galleryIntent)
        }
    }

    private fun convertMediaUriToPath(uri: Uri): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context?.contentResolver?.query(uri, proj, null, null, null)
        val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val path = cursor.getString(columnIndex)
        cursor.close()

        return path
    }

    private fun scanQRFromImage(file: File) {
        val inputStream: InputStream = BufferedInputStream(FileInputStream(file))
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val scanResult = decodeCode(bitmap)
        makeToast(requireContext(), scanResult)
    }

    private fun decodeCode(bMap: Bitmap): String? {
        var contents: String? = null
        val intArray = IntArray(bMap.width * bMap.height)

        bMap.getPixels(intArray, 0, bMap.width, 0, 0, bMap.width, bMap.height)
        val source: LuminanceSource = RGBLuminanceSource(bMap.width, bMap.height, intArray)
        val bitmap = BinaryBitmap(HybridBinarizer(source))
        val reader: Reader = MultiFormatReader()
        try {
            val result: Result = reader.decode(bitmap)
            contents = result.text
        } catch (e: Exception) {
            makeToast(requireContext(), "Gambar gagal diproses")
        }

        return contents
    }

    private fun checkCameraPermissionAndOpenCamera() {
        when {
            // Izin sudah diberikan, langsung buka kamera
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED -> {
                codeScanner.startPreview()
            }

            // Tampilkan alasan mengapa izin dibutuhkan dan kemudian minta izin
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                makeToast(requireContext(), "Izin kamera dibutuhkan untuk mengambil gambar")
                resultCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }

            else -> {
                resultCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        super.onPause()
        codeScanner.releaseResources()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        codeScanner.releaseResources()
        _binding = null
    }
}