package com.eniskaner.mcutrackers.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

    private fun addOnDestinationChangedListener(navController: NavController): NavController {
        return navController.apply {
            addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.movieFragment, R.id.favouriteFragment ->
                        setBottomNavigationShowAnimation()
                    else -> setBottomNavigationHideAnimation()
                }
            }
        }
    }
    private fun setBottomNavigationShowAnimation() {
        binding.bottomNavigation.takeIf { it.visibility == View.GONE }?.let {
            it.animate().alpha(1f).setListener(
                object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator) {
                        super.onAnimationStart(animation)
                        it.visibility = View.VISIBLE
                    }
                }
            )
        }
    }
    private fun setBottomNavigationHideAnimation() {
        binding.bottomNavigation.takeIf { it.visibility == View.VISIBLE }?.let {
            it.animate().alpha(0f).setListener(
                object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        it.visibility = View.GONE
                    }
                }
            )
        }
    }

    private fun initDecorFitsSystemWindows() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}