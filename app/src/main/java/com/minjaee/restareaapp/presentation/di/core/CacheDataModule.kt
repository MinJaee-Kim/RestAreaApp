package com.minjaee.restareaapp.presentation.di.core

import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionCacheDataSource
import com.minjaee.restareaapp.data.repository.direction.datasourceimpl.DirectionCacheDataSourceImpl
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaFoodCacheDataSource
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaRoomCacheDataSource
import com.minjaee.restareaapp.data.repository.restarea.datasourceimpl.RestAreaFoodCacheDataSourceImpl
import com.minjaee.restareaapp.data.repository.restarea.datasourceimpl.RestAreaRoomCacheDataSourceImpl
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
    fun provideRestAreaRoomCacheDataSource(): RestAreaRoomCacheDataSource {
        return RestAreaRoomCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideRestAreaFoodCacheDataSource(): RestAreaFoodCacheDataSource {
        return RestAreaFoodCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideDirectionCacheDataSource(): DirectionCacheDataSource {
        return DirectionCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideSearchCacheDataSource(): SearchCacheDataSource {
        return SearchCacheDataSourceImpl()
    }
}