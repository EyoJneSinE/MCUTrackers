package com.eniskaner.mcutrackers.ui.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.eniskaner.mcutrackers.R
import com.eniskaner.mcutrackers.base.BaseFragment
import com.eniskaner.mcutrackers.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding> (R.layout.fragment_movie) {
    private val viewModel by viewModels<MovieViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindingVariables()
        initToolbarMenuClickListener()
    }

    private fun initBindingVariables() {
        with(binding) {
            vm = viewModel
            adapter = MovieAdapter()
            executePendingBindings()
        }
    }

    private fun initToolbarMenuClickListener() {
        binding.toolbarMovie.setOnMenuItemClickListener { menu ->
            viewModel.setPhase(menu.itemId)
            true
        }
    }
}