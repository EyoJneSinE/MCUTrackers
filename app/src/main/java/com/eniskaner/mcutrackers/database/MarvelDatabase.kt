package com.eniskaner.mcutrackers.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eniskaner.mcutrackers.database.model.Movie
import com.eniskaner.mcutrackers.database.model.Rating

@Database(
    entities = [Movie::class, Rating::class],
    version = 1,
    exportSchema = false
)
abstract class MarvelDatabase: RoomDatabase(){
    abstract fun marvelDao(): MarvelDao
}