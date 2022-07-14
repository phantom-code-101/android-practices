package com.demo.shared.database

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "HackerNews")
data class HackerNewsDao(

    @ColumnInfo(name = "newsId")
    val newsId: String,

    @ColumnInfo(name = "message")
    val message: String
)