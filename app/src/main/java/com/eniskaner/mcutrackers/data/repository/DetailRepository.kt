package com.eniskaner.mcutrackers.data.repository

import com.eniskaner.mcutrackers.data.model.MovieDetailInfo
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getMovie(title: String): Flow<MovieDetailInfo>

    fun isFavourite(title: String): Flow<Boolean>

    suspend fun insertRating(title: String, rating: Float)
}