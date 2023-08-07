package com.ballflight6463.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ballflight6463.models.Leader

@Database(exportSchema = false, version=1, entities = [Leader::class])
abstract class AppDatabase : RoomDatabase(){
    abstract fun dao():BallDao
}