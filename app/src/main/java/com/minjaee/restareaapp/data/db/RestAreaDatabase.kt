package com.minjaee.restareaapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.minjaee.restareaapp.data.model.getdirection.GetDirections

@Database(
    entities = [GetDirections::class],
    version = 1,
    exportSchema = false
)
abstract class RestAreaDatabase : RoomDatabase() {
    abstract fun getDirectionDao() : DirectionDAO
}