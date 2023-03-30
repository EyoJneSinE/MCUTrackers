package com.eniskaner.mcutrackers.data.repository

import com.eniskaner.mcutrackers.database.model.Movie
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getMovie(title: String): Flow<Movie>

    suspend fun insertRating(title: String, rating: Float)
}