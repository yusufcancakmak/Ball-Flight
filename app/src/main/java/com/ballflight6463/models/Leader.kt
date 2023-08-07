package com.ballflight6463.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leader")
data class Leader(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val score: Int
){

}