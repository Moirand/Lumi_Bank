package com.example.feature_dashboard.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.common.NavigationHandler
import com.example.feature_dashboard.databinding.FragmentAccountBinding
import com.example.feature_dashboard.viewModel.AccountViewModel
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    private val navHandler: NavigationHandler by inject()
    private val viewModel by viewModel<AccountViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentAccountBinding.inflate(layoutInflater).also {
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.linearlayNotifikasi.setOnClickListener {

        }

        binding.linearlayKeluarAkun.setOnClickListener {
            lifecycleScope.launch {
                awaitAll(
                    viewModel.deleteToken(),
                    viewModel.deleteUserData()
                )
                navHandler.navigateToAuthNavigation()
            }
        }

        viewModel.localUserData.observe(viewLifecycleOwner) { userData ->
            binding.tvNamaPengguna.text = userData.name
            binding.tvEmailPengguna.text = userData.email
            binding.tvNoHpPengguna.text = userData.noHp
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}