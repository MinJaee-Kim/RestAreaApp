package com.minjaee.restareaapp.data.db

import androidx.room.*
import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap

@Database(
    entities = [SearchMap::class],
    version = 1,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class SearchLogDatabase : RoomDatabase() {
    abstract fun searchLogDAO() : SearchLogDAO
}