package com.minjaee.restareaapp.data.db

import androidx.room.*
import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap

@Dao
interface SearchLogDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSearch(searchLog : List<SearchMap>)

    @Delete
    suspend fun deleteOne(document: SearchMap)

    @Query("SELECT * FROM search")
    suspend fun getSearchLogs():List<SearchMap>
}