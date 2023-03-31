package com.eniskaner.mcutrackers.ui.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.eniskaner.mcutrackers.data.model.MovieAndRating
import com.eniskaner.mcutrackers.databinding.ItemFavouriteBinding
import com.eniskaner.mcutrackers.util.MarvelViewHolder

class FavouriteAdapter:ListAdapter<MovieAndRating, MarvelViewHolder<MovieAndRating>>(
    object : DiffUtil.ItemCallback<MovieAndRating>() {
        override fun areItemsTheSame(oldItem: MovieAndRating, newItem: MovieAndRating): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieAndRating, newItem: MovieAndRating): Boolean {
            return oldItem==newItem
        }

    }
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelViewHolder<MovieAndRating> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavouriteBinding.inflate(inflater, parent, false)
        return MarvelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarvelViewHolder<MovieAndRating>, position: Int) {
        holder.bind(getItem(position))
    }

}