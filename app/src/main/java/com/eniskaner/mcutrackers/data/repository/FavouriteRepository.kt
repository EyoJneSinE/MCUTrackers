package com.eniskaner.mcutrackers.data.repository

import com.eniskaner.mcutrackers.data.model.MovieAndRating
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    fun getFavourites(): Flow<List<MovieAndRating>>
}