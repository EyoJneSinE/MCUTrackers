package com.eniskaner.mcutrackers.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.eniskaner.mcutrackers.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initMoviesCollect()
        initToolbarMenuClickListener()
    }

    private fun initToolbarMenuClickListener() {
        binding.toolbarMovie.setOnMenuItemClickListener { menu ->
            viewModel.setPhase(menu.itemId)
            true
        }
    }

    private fun initMoviesCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movies.collect { list ->
                    (binding.rwMovie.adapter as MovieAdapter).submitList(list)
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.rwMovie.also { it.setHasFixedSize(true) }.adapter = MovieAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}