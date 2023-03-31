package com.eniskaner.mcutrackers.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eniskaner.mcutrackers.data.model.MovieAndRating
import com.eniskaner.mcutrackers.data.model.MovieBasicInfo
import com.eniskaner.mcutrackers.ui.favourite.FavouriteAdapter
import com.eniskaner.mcutrackers.ui.movie.MovieAdapter

@BindingAdapter("initRwMovie")
fun RecyclerView.init(adapter: MovieAdapter) {
    also { it.setHasFixedSize(true) }.adapter = adapter
}

@BindingAdapter("initRwFavourite")
fun RecyclerView.init(adapter: FavouriteAdapter) {
    also { it.setHasFixedSize(true) }.adapter = adapter
}

@BindingAdapter("submitMovies")
fun RecyclerView.submitMovies(movies: List<MovieBasicInfo>) {
    (adapter as? MovieAdapter)?.submitList(movies)
}

@BindingAdapter("submitFavourites")
fun RecyclerView.submitFavourites(favourites: List<MovieAndRating>) {
    (adapter as? FavouriteAdapter)?.submitList(favourites)
}

@BindingAdapter("setImageWithUrl")
fun ImageView.setImageWithUrl(url: String?) {
    url?.let {
        Glide.with(context)
            .load(url)
            .into(this)
    }
}
