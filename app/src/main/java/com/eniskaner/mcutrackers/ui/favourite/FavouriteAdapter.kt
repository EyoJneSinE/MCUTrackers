package com.eniskaner.mcutrackers.ui.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.mcutrackers.database.model.Rating
import com.eniskaner.mcutrackers.databinding.ItemFavouriteBinding

class FavouriteAdapter:ListAdapter<Rating, FavouriteAdapter.FavouriteViewHolder>(
    object : DiffUtil.ItemCallback<Rating>() {
        override fun areItemsTheSame(oldItem: Rating, newItem: Rating): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Rating, newItem: Rating): Boolean {
            return oldItem==newItem
        }

    }
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavouriteBinding.inflate(inflater, parent, false)
        return FavouriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class FavouriteViewHolder(private val binding: ItemFavouriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Rating) {
            binding.model = model
            binding.executePendingBindings()
        }
    }
}