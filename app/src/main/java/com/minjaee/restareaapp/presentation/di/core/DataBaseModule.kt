package com.minjaee.restareaapp.presentation.di.core

import android.app.Application
import androidx.room.Room
import com.minjaee.restareaapp.data.db.SearchLogDAO
import com.minjaee.restareaapp.data.db.SearchLogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun provideSearchLogDatabase(app: Application):SearchLogDatabase {
        return Room.databaseBuilder(app, SearchLogDatabase::class.java, "search_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideSearchLogDao(searchLogDatabase: SearchLogDatabase):SearchLogDAO{
        return searchLogDatabase.searchLogDAO
    }
}