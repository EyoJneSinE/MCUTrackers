package com.eniskaner.mcutrackers.data

import com.eniskaner.mcutrackers.database.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movie>>

    fun getMoviesByPhase(phase: Int): Flow<List<Movie>>
}