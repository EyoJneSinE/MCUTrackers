package com.eniskaner.mcutrackers.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.eniskaner.mcutrackers.R
import com.eniskaner.mcutrackers.base.BaseFragment
import com.eniskaner.mcutrackers.databinding.FragmentDetailBinding
import com.eniskaner.mcutrackers.util.NavigateCallBack
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private val args by navArgs<DetailFragmentArgs>()
    @Inject
    lateinit var viewModelAssistedFactory: DetailViewModel.DetailAssistedFactory
    private val viewModel by viewModels<DetailViewModel> {
        DetailViewModel.provideDetailAssistedFactory(viewModelAssistedFactory, args.title)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindingVariables()
        initToolbarNavigationClickListener()
        initRatingBarChangeListener()
        initFragmentResultListener()
    }

    private fun initBindingVariables() {
        with(binding) {
            vm = viewModel
            callback = NavigateCallBack { view, title ->  navigateRatingDialog(view, title) }
            executePendingBindings()
        }
    }
    private fun navigateRatingDialog(view: View, title: String?) {
        title?.let {
            val action = DetailFragmentDirections.actionDetailFragmentToRatingDialog(title)
            view.findNavController().navigate(action)
        }
    }
    private fun initToolbarNavigationClickListener() {
        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initRatingBarChangeListener() {
        binding.ratingDetail.setOnRatingBarChangeListener { _, value, _ ->
            viewModel.changeRating(value)
        }
    }

    private fun initFragmentResultListener() {
        setFragmentResultListener("rating") { _, bundle ->
            viewModel.insertRating(bundle.getFloat("rating"))
            Snackbar.make(binding.root, "Star ratings are saved.", Snackbar.LENGTH_SHORT).show()
        }
    }
}