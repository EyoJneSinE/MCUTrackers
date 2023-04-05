package com.eniskaner.mcutrackers.data.repository

import com.eniskaner.mcutrackers.data.model.MovieAndRating
import com.eniskaner.mcutrackers.database.MarvelDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FavouriteRepositoryImplementation @Inject constructor(
    private val dao: MarvelDao,
    private val ioDispatcher: CoroutineDispatcher
): FavouriteRepository {
    override fun getFavourites(): Flow<List<MovieAndRating>> {
        return dao.getFavourites().flowOn(ioDispatcher)
    }

}