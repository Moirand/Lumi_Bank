package com.example.feature_auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.common.NavigationHandler
import com.example.common.makeToast
import com.example.core.Resource
import com.example.feature_auth.databinding.FragmentLoginBinding
import com.example.feature_auth.viewModel.LoginViewModel
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<LoginViewModel>()
    private val navHandler: NavigationHandler by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentLoginBinding.inflate(layoutInflater).also {
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnKembali.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnMasuk.setOnClickListener {
            viewModel.login(
                binding.tiedtEmail.text.toString(),
                binding.tiedtPassword.text.toString()
            )
        }

        viewModel.loginResult.observe(viewLifecycleOwner) { loginResult ->
            when (loginResult) {
                is Resource.Loading -> handleLoading(true)
                is Resource.Error -> {
                    handleLoading(false)
                    makeToast(requireContext(), loginResult.message)
                }

                is Resource.Success -> {
                    handleLoading(false)
                    lifecycleScope.launch {
                        awaitAll(
                            viewModel.saveToken(loginResult.data?.data?.jwtToken ?: "")
                        )
                        viewModel.getRemoteUserData(loginResult.data?.data?.jwtToken ?: "")
                    }
                }
            }
        }

        viewModel.remoteUserData.observe(viewLifecycleOwner) { userGetResult ->
            when (userGetResult) {
                is Resource.Loading -> handleLoading(true)
                is Resource.Error -> {
                    handleLoading(false)
                    makeToast(requireContext(), userGetResult.message)
                }

                is Resource.Success -> {
                    handleLoading(false)
                    lifecycleScope.launch {
                        awaitAll(
                            viewModel.saveUserData(userGetResult.data?.data)
                        )
                        navHandler.navigateToDashboardNavigation()
                    }
                }
            }
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