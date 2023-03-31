package com.eniskaner.mcutrackers.data.repository

import com.eniskaner.mcutrackers.database.MarvelDao
import com.eniskaner.mcutrackers.database.model.Rating
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RatingRepositoryImplementation @Inject constructor(
    private val dao: MarvelDao,
    private val ioDispatcher: CoroutineDispatcher
): RatingRepository {
    override fun getExistsRating(title: String): Flow<Boolean> {
        return dao.getExistRating(title).flowOn(ioDispatcher)
    }

    override suspend fun insertRating(title: String, rating: Float) {
        dao.insertRating(Rating(title = title, rating = rating))
    }
}