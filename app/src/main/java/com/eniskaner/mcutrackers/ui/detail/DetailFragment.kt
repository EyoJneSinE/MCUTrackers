package com.eniskaner.mcutrackers.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.eniskaner.mcutrackers.databinding.FragmentDetailBinding
import com.eniskaner.mcutrackers.util.NavigateCallBack
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val args by navArgs<DetailFragmentArgs>()

    @Inject
    lateinit var viewModelAssistedFactory: DetailViewModel.DetailAssistedFactory

    private val viewModel by viewModels<DetailViewModel> {
        DetailViewModel.provideDetailAssistedFactory(viewModelAssistedFactory, args.title)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavigateCallBack()
        initMovieCollect()
        initToolbarNavigationClickListener()
        initFragmentResultListener()
    }

    private fun initFragmentResultListener() {
        setFragmentResultListener("rating") { _ , bundle ->
            viewModel.insertRating(bundle.getFloat("rating"))
            Snackbar.make(binding.root, "Star ratings saved.", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun initNavigateCallBack() {
        binding.callback = object : NavigateCallBack {
            override fun navigate(view: View, title: String) {
                val action = DetailFragmentDirections.actionDetailFragmentToRatingDialog(title)
                view.findNavController().navigate(action)
            }
        }
        binding.executePendingBindings()
    }

    private fun initToolbarNavigationClickListener() {
        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initMovieCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movie.collect {
                    binding.movie = it
                    binding.executePendingBindings()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}