package com.minjaee.restareaapp.presentation.di.core

import com.minjaee.restareaapp.data.db.SearchLogDAO
import com.minjaee.restareaapp.data.repository.search.datasource.SearchLocalDataSource
import com.minjaee.restareaapp.data.repository.search.datasourceimpl.SearchLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideSearchLocalDataSource(searchLogDAO: SearchLogDAO):SearchLocalDataSource{
        return SearchLocalDataSourceImpl(searchLogDAO)
    }
}