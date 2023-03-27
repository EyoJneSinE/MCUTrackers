package com.eniskaner.mcutrackers.database.model

import android.icu.text.CaseMap.Title
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 class Movie(
    @PrimaryKey val id: Int,
    val title: String,
    val phase: Int,
    val content: String,
    val release: String,
    @ColumnInfo(name = "running_time") val runningTime: Int,
    val image: String
)