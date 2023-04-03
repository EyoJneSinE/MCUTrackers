package com.eniskaner.mcutrackers.data.repository

import android.support.annotation.MenuRes
import com.eniskaner.mcutrackers.data.model.MovieBasicInfo
import com.eniskaner.mcutrackers.database.model.Movie
import com.eniskaner.mcutrackers.database.model.Phase
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<MovieBasicInfo>>

    fun getMoviesByPhase(phase: Int): Flow<List<MovieBasicInfo>>

    fun getPhase(): Flow<Phase>

    suspend fun setPhase(@MenuRes menuId: Int)
}