package com.eniskaner.mcutrackers.ui.favourite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.eniskaner.mcutrackers.R
import com.eniskaner.mcutrackers.base.BaseFragment
import com.eniskaner.mcutrackers.databinding.FragmentFavouriteBinding
import com.eniskaner.mcutrackers.util.addOnScrollListenerForJankStats
import com.eniskaner.mcutrackers.util.getMetricsStateHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>(R.layout.fragment_favourite) {
    private val viewModel by viewModels<FavouriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindingVariables()
        initScrollListenerForJankStats()
    }

    private fun initBindingVariables() {
        with(binding) {
            vm = viewModel
            adapter = FavouriteAdapter()
            executePendingBindings()
        }
    }

    private fun initScrollListenerForJankStats() {
        val holder = binding.getMetricsStateHolder()
        binding.rwFavourite.addOnScrollListenerForJankStats(holder)
    }
}