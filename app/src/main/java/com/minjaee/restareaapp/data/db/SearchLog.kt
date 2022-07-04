package com.minjaee.restareaapp.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_log_data_table")
data class SearchLog(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "search_log_id")
    var id : Int,
    @ColumnInfo(name = "search_log_name")
    var name : String
)