package com.eniskaner.mcutrackers.data

import com.eniskaner.mcutrackers.database.MarvelDao
import com.eniskaner.mcutrackers.database.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImplementation @Inject constructor(
    private val dao: MarvelDao,
    private val ioDispatcher: CoroutineDispatcher
): MovieRepository {
    override fun getMovies(): Flow<List<Movie>> {
        return dao.getMovies().flowOn(ioDispatcher)
    }
}