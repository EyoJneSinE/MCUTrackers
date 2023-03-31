package com.eniskaner.mcutrackers.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.eniskaner.mcutrackers.R
import com.eniskaner.mcutrackers.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initBottomNavigationView()
        initDecorFitsSystemWindows()
    }
    private fun initBottomNavigationView() {
        binding.bottomNavigation.setupWithNavController(getNavController())
    }

    private fun getNavController(): NavController {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }

    private fun initDecorFitsSystemWindows() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}