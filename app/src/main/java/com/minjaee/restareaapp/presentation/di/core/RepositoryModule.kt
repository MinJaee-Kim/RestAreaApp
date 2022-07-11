package com.minjaee.restareaapp.presentation.di.core

import com.minjaee.restareaapp.data.repository.direction.DirectionRepositoryImpl
import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionCacheDataSource
import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionRemoteDataSource
import com.minjaee.restareaapp.data.repository.restarea.RestAreaRepositoryImpl
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaFoodCacheDataSource
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaFoodRemoteDataSource
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaRoomCacheDataSource
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaRoomRemoteDataSource
import com.minjaee.restareaapp.data.repository.search.SearchRepositoryImpl
import com.minjaee.restareaapp.data.repository.search.datasource.SearchCacheDataSource
import com.minjaee.restareaapp.data.repository.search.datasource.SearchLocalDataSource
import com.minjaee.restareaapp.data.repository.search.datasource.SearchNoLocationRemoteDataSource
import com.minjaee.restareaapp.data.repository.search.datasource.SearchRemoteDataSource
import com.minjaee.restareaapp.domain.repository.DirectionRepository
import com.minjaee.restareaapp.domain.repository.RestAreaRepository
import com.minjaee.restareaapp.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRestAreaRepository(
        restAreaFoodCacheDataSource: RestAreaFoodCacheDataSource,
        restAreaFoodRemoteDataSource: RestAreaFoodRemoteDataSource,
        restAreaRoomCacheDataSource: RestAreaRoomCacheDataSource,
        restAreaRoomRemoteDataSource: RestAreaRoomRemoteDataSource
    ):RestAreaRepository{
        return RestAreaRepositoryImpl(
            restAreaFoodCacheDataSource,
            restAreaFoodRemoteDataSource,
            restAreaRoomCacheDataSource,
            restAreaRoomRemoteDataSource
        )
    }

    @Singleton
    @Provides
    fun provideDirectionRepository(
        directionCacheDataSource: DirectionCacheDataSource,
        directionRemoteDataSource: DirectionRemoteDataSource
    ):DirectionRepository{
        return  DirectionRepositoryImpl(
            directionCacheDataSource,
            directionRemoteDataSource
        )
    }

    @Singleton
    @Provides
    fun provideSearchRepository(
        searchCacheDataSource: SearchCacheDataSource,
        searchLocalDataSource: SearchLocalDataSource,
        searchRemoteDataSource: SearchRemoteDataSource,
        searchNoLocationRemoteDataSource: SearchNoLocationRemoteDataSource
    ):SearchRepository{
        return SearchRepositoryImpl(
            searchCacheDataSource,
            searchLocalDataSource,
            searchRemoteDataSource,
            searchNoLocationRemoteDataSource
        )
    }
}
