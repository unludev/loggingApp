package com.example.android.hilt.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "logs")
data class Log(val msg: String, val timestamp: Long) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
