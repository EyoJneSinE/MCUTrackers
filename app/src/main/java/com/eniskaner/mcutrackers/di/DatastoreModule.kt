package com.eniskaner.mcutrackers.di

import android.content.Context
import com.eniskaner.mcutrackers.datastore.MarvelDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn
object DatastoreModule {
    @Provides
    @Singleton
    fun provideMarvelDatastore(
        @ApplicationContext context: Context
    ): MarvelDatastore {
        return MarvelDatastore(context)
    }
}