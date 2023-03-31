package com.eniskaner.mcutrackers.data.repository

import com.eniskaner.mcutrackers.data.model.MovieDetailInfo
import com.eniskaner.mcutrackers.database.MarvelDao
import com.eniskaner.mcutrackers.database.model.Rating
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepositoryImplementation @Inject constructor(
    private val dao: MarvelDao,
    private val ioDispatcher: CoroutineDispatcher
): DetailRepository {
    override fun getMovie(title: String): Flow<MovieDetailInfo> {
        return dao.getMovie(title).flowOn(ioDispatcher)
    }
}