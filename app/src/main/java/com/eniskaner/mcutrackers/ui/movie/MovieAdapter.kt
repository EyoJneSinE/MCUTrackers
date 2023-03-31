package com.eniskaner.mcutrackers.ui.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.eniskaner.mcutrackers.base.BaseDiffUtilItemCallBack
import com.eniskaner.mcutrackers.data.model.MovieBasicInfo
import com.eniskaner.mcutrackers.databinding.ItemMovieBinding
import com.eniskaner.mcutrackers.util.MarvelViewHolder

class MovieAdapter : ListAdapter<MovieBasicInfo, MarvelViewHolder<MovieBasicInfo>>(
    BaseDiffUtilItemCallBack(
        areItemsSame = {oldItem, newItem -> oldItem.id == newItem.id },

        areContentsSame = {oldItem, newItem -> oldItem == newItem }
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelViewHolder<MovieBasicInfo> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MarvelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarvelViewHolder<MovieBasicInfo>, position: Int) {
        holder.bind(getItem(position))
    }

}