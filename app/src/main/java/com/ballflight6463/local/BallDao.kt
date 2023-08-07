package com.ballflight6463.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ballflight6463.models.Leader
import kotlinx.coroutines.flow.Flow
@Dao
interface BallDao {
    @Query("SELECT * FROM leader ORDER BY score DESC")
    fun getAllLeaders(): Flow<List<Leader>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeader(item: Leader)
}