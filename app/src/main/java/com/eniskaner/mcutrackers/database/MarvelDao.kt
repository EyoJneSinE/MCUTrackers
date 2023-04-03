package com.eniskaner.mcutrackers.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eniskaner.mcutrackers.data.model.MovieAndRating
import com.eniskaner.mcutrackers.data.model.MovieBasicInfo
import com.eniskaner.mcutrackers.data.model.MovieDetailInfo
import com.eniskaner.mcutrackers.database.model.Movie
import com.eniskaner.mcutrackers.database.model.Rating
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("SELECT id, title, image FROM Movie")
    fun getMovies(): Flow<List<MovieBasicInfo>>

    @Query("SELECT id, title, image FROM Movie WHERE phase = :phase")
    fun getMoviesByPhase(phase: Int): Flow<List<MovieBasicInfo>>

    @Query(
        """
            SELECT e.id, e.title, e.image, r.rating
            FROM Movie AS e, Rating AS r
            WHERE e.title = r.title
            ORDER BY r.id
        """
    )
    fun getFavourites(): Flow<List<MovieAndRating>>

    @Query("SELECT id, title, content, `release`, running_time AS runningTime, image FROM Movie WHERE title = :title")
    fun getMovie(title: String): Flow<MovieDetailInfo>

    @Query("SELECT rating FROM Rating WHERE title = :title)")
    fun getRating(title: String): Flow<Float>

    @Insert
    suspend fun insertRating(rating: Rating)

    @Query("UPDATE Rating SET rating = :rating WHERE title = :title")
    suspend fun updateRating(title: String, rating: Float)

    @Query("DELETE FROM Rating WHERE title = :title")
    suspend fun deleteRating(title: String)

}