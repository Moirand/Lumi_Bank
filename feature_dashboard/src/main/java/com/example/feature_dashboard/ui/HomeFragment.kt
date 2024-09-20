package com.example.feature_dashboard.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.common.NavigationHandler
import com.example.common.hideAccountNumber
import com.example.common.hideBalance
import com.example.common.makeToast
import com.example.common.toRupiah
import com.example.core.Resource
import com.example.feature_dashboard.databinding.FragmentHomeBinding
import com.example.feature_dashboard.viewModel.HomeViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("SetTextI18n")
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<HomeViewModel>()
    private val navHandler: NavigationHandler by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(layoutInflater).also {
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.localUserData.observe(viewLifecycleOwner) { userData ->
            viewModel.getBalance(userData.accountNumber)
            binding.tvSalamSambutan.text = "Selamat Datang, ${userData.name}"
            binding.lumiCard.tvNama.text = userData.name
            binding.lumiCard.toggleRekening.apply {
                text = userData.accountNumber.hideAccountNumber()
                textOn = userData.accountNumber
                textOff = userData.accountNumber.hideAccountNumber()
            }
        }

        viewModel.balanceGetResult.observe(viewLifecycleOwner) { balance ->
            when (balance) {
                is Resource.Loading -> handleLoading(true)
                is Resource.Error -> {
                    handleLoading(false)
                    makeToast(requireContext(), balance.message)
                }

                is Resource.Success -> {
                    handleLoading(false)
                    binding.lumiCard.toggleSaldo.apply {
                        text = balance.data?.data.toString().hideBalance()
                        textOn = balance.data?.data.toString().toRupiah()
                        textOff = balance.data?.data.toString().hideBalance()
                    }
                }
            }
        }

        binding.lumiCard.btnTransfer.setOnClickListener {

        }

        binding.lumiCard.btnMutasi.setOnClickListener {
            navHandler.navigateToMutasiNavigation()
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}