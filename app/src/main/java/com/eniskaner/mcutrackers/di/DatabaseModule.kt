package com.eniskaner.mcutrackers.di

import android.content.Context
import androidx.room.Room
import com.eniskaner.mcutrackers.database.MarvelDao
import com.eniskaner.mcutrackers.database.MarvelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): MarvelDatabase {
        return Room.databaseBuilder(
            context,
            MarvelDatabase::class.java,
            "Marvel.db"
        ).build()
    }
    @Provides
    @Singleton
    fun provideMarvelDao(db: MarvelDatabase): MarvelDao {
        return db.marvelDao()
    }
}