package com.eniskaner.mcutrackers.data.repository

import com.eniskaner.mcutrackers.data.model.MovieDetailInfo
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getMovie(title: String): Flow<MovieDetailInfo>
}