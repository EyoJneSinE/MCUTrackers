package com.eniskaner.mcutrackers.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eniskaner.mcutrackers.database.model.Movie
import com.eniskaner.mcutrackers.database.model.Rating
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Insert
    suspend fun insertRating(rating: Rating)

    @Query("SELECT*FROM Movie")
    fun getMovies(): Flow<List<Movie>>

    @Query("SELECT*FROM Movie WHERE phase = :phase")
    fun getMoviesByPhase(phase: Int): Flow<List<Movie>>

    @Query("SELECT*FROM Movie WHERE title = :title")
    fun getMovie(title: String): Flow<Movie>

    @Query("SELECT EXISTS(SELECT 1 FROM Rating WHERE title = :title)")
    fun getIsFavourite(title: String): Flow<Boolean>
}