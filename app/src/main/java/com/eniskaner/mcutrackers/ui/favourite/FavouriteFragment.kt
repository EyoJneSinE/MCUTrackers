package com.eniskaner.mcutrackers.ui.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eniskaner.mcutrackers.R
import com.eniskaner.mcutrackers.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment() {
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = requireNotNull(_binding)

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
    }

    private fun initRecyclerView() {
        binding.rwFavourite.also { it.setHasFixedSize(true) }.adapter = FavouriteAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}