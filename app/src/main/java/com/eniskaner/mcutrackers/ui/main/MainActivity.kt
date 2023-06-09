package com.eniskaner.mcutrackers.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import androidx.metrics.performance.JankStats
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.eniskaner.mcutrackers.R
import com.eniskaner.mcutrackers.databinding.ActivityMainBinding
import com.eniskaner.mcutrackers.util.getMetricsStateHolder
import com.eniskaner.mcutrackers.util.putState
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val holder by lazy {
        binding.getMetricsStateHolder()
    }
    private lateinit var jankStats: JankStats
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initJankStats()
        initBottomNavigationView()
        initDecorFitsSystemWindows()
    }

    private fun initJankStats() {
        jankStats = JankStats.createAndTrack(window) { frameData ->
            frameData.takeIf { it.isJank }?.let { Timber.d(frameData.toString()) }
        }
    }

    private fun initBottomNavigationView() {
        binding.bottomNavigation.setupWithNavController(getNavController())
    }

    private fun getNavController(): NavController {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return addOnDestinationChangedListener(navHostFragment.navController)
    }

    private fun addOnDestinationChangedListener(navController: NavController): NavController {
        return navController.apply {
            addOnDestinationChangedListener { _, destination, _ ->
                setBottomNavigationVisibility(destination)
                putStateInMetricsStateHolder(destination)
                }
            }
        }

    private fun setBottomNavigationVisibility(destination: NavDestination) {
        when (destination.id) {
            R.id.movieFragment, R.id.favouriteFragment -> setBottomNavigationShowAnimation()
            else -> setBottomNavigationHideAnimation()
    }
}
    private fun putStateInMetricsStateHolder(destination: NavDestination) {
        holder.putState("CurrentDestination", "${destination.label}")
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

    override fun onResume() {
        super.onResume()
        jankStats.isTrackingEnabled = true
    }

    override fun onPause() {
        super.onPause()
        jankStats.isTrackingEnabled = false
    }
}