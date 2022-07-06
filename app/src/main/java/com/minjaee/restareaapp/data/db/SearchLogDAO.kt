package com.minjaee.restareaapp.data.db

import androidx.room.*
import com.minjaee.restareaapp.data.model.keywordsearch.Document

@Dao
interface SearchLogDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSearch(searchLog : List<Document>)

    @Delete
    suspend fun deleteOne(document: Document)

    @Query("SELECT * FROM search_log_data_table")
    suspend fun getSearchLogs():List<Document>
}