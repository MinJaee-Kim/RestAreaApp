package com.minjaee.restareaapp.presentation.di.core

import com.minjaee.restareaapp.data.repository.search.datasource.SearchCacheDataSource
import com.minjaee.restareaapp.data.repository.search.datasourceimpl.SearchCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheDataModule {
    @Singleton
    @Provides
    fun provideSearchCacheDataSource(): SearchCacheDataSource {
        return SearchCacheDataSourceImpl()
    }
}