package com.example.lumibank

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.common.NavigationHandler
import com.example.lumibank.databinding.ActivityMainBinding
import org.koin.android.ext.android.getKoin

class MainActivity : AppCompatActivity(), NavigationHandler {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        getKoin().declare(this as NavigationHandler)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Mengatur kemunculan bottom navigasi pada fragment tertentu
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == com.example.feature_dashboard.R.id.homeFragment
                || destination.id == com.example.feature_dashboard.R.id.accountFragment
            ) {
                binding.bottomNav.visibility = View.VISIBLE
            } else {
                binding.bottomNav.visibility = View.GONE
            }
        }

        NavigationUI.setupWithNavController(binding.bottomNav, navController)

        // Mengatur navigasi bottom navigation
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    navController.navigate(com.example.feature_dashboard.R.id.homeFragment)
                    true
                }

                R.id.menu_account -> {
                    navController.navigate(com.example.feature_dashboard.R.id.accountFragment)
                    true
                }

                else -> false
            }
        }
    }

    override fun navigateToAuthNavigation() {
        navController.navigate(R.id.dashboardNavigation_to_authNavigation)
    }

    override fun navigateToDashboardNavigation() =
        navController.navigate(R.id.authNavigation_to_dashboardNavigation)

    override fun navigateToMutasiNavigation() {
        TODO("Not yet implemented")
    }

    override fun navigateToTransferNavigation() {
        TODO("Not yet implemented")
    }
}