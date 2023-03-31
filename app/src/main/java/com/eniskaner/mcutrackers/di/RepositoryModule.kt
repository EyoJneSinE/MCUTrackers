package com.eniskaner.mcutrackers.di

import com.eniskaner.mcutrackers.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieRepositoryImplementation: MovieRepositoryImplementation
    ): MovieRepository

    @Binds
    @Singleton
    abstract fun bindFavouriteRepository(
        favouriteRepositoryImplementation: FavouriteRepositoryImplementation
    ): FavouriteRepository

    @Binds
    @Singleton
    abstract fun bindDetailRepository(
        detailRepositoryImplementation: DetailRepositoryImplementation
    ): DetailRepository

    @Binds
    @Singleton
    abstract fun bindRatingRepository(
        ratingRepositoryImplementation: RatingRepositoryImplementation
    ): RatingRepository
}