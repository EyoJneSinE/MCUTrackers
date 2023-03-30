package com.eniskaner.mcutrackers.ui.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.eniskaner.mcutrackers.R
import com.eniskaner.mcutrackers.databinding.FragmentFavouriteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteFragment : Fragment() {
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel by viewModels<FavouriteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initFavouritesCollect()
    }

    private fun initFavouritesCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favourites.collect { list ->
                    (binding.rwFavourite.adapter as FavouriteAdapter).submitList(list)
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.rwFavourite.also { it.setHasFixedSize(true) }.adapter = FavouriteAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}