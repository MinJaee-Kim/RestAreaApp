package com.minjaee.restareaapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.minjaee.restareaapp.data.model.keywordsearch.Document

@Database(entities = [Document::class], version = 1)
abstract class SearchLogDatabase : RoomDatabase() {
    abstract val searchLogDAO : SearchLogDAO

    companion object{
        @Volatile
        private var INSTANCE : SearchLogDatabase? = null
        fun getInstance(context: Context):SearchLogDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SearchLogDatabase::class.java,
                        "search_log_data_table"
                    ).build()
                }
                return instance
            }
        }
    }
}