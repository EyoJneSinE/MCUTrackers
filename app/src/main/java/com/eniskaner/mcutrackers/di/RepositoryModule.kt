package com.eniskaner.mcutrackers.di

import com.eniskaner.mcutrackers.data.MovieRepository
import com.eniskaner.mcutrackers.data.MovieRepositoryImplementation
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
}