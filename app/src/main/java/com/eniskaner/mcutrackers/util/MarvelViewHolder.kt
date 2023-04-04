package com.eniskaner.mcutrackers.util

import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.mcutrackers.BR
import com.eniskaner.mcutrackers.NavGraphDirections

class MarvelViewHolder<T>(
    private val binding: ViewDataBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(model: T) {
        with(binding) {
            setVariable(BR.model, model)
            setVariable(BR.callback, callback)
            executePendingBindings()
        }
    }
    companion object {
        private val callback = NavigateCallBack { view, title ->
            title?.let {
                val action = NavGraphDirections.actionGlobalDetailFragment(title)
                view.findNavController().navigate(action)
            }
        }
    }
}