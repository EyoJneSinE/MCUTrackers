package com.eniskaner.mcutrackers.ui.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.mcutrackers.NavGraphDirections
import com.eniskaner.mcutrackers.data.model.MovieBasicInfo
import com.eniskaner.mcutrackers.database.model.Movie
import com.eniskaner.mcutrackers.databinding.ItemMovieBinding
import com.eniskaner.mcutrackers.util.MarvelViewHolder
import com.eniskaner.mcutrackers.util.NavigateCallBack

class MovieAdapter : ListAdapter<MovieBasicInfo, MarvelViewHolder<MovieBasicInfo>>(
    object : DiffUtil.ItemCallback<MovieBasicInfo>() {

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: MovieBasicInfo, newItem: MovieBasicInfo): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: MovieBasicInfo, newItem: MovieBasicInfo): Boolean {
            return oldItem.id == newItem.id
        }
    }
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