package com.minjaee.restareaapp.presentation.di.core

import com.minjaee.restareaapp.data.api.RestAreaService
import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionRemoteDataSource
import com.minjaee.restareaapp.data.repository.direction.datasourceimpl.DirectionRemoteDataSourceImpl
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaFoodRemoteDataSource
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaRoomRemoteDataSource
import com.minjaee.restareaapp.data.repository.restarea.datasourceimpl.RestAreaFoodRemoteDataSourceImpl
import com.minjaee.restareaapp.data.repository.restarea.datasourceimpl.RestAreaRoomRemoteDataSourceImpl
import com.minjaee.restareaapp.data.repository.search.datasource.SearchRemoteDataSource
import com.minjaee.restareaapp.data.repository.search.datasourceimpl.SearchRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {
    @Singleton
    @Provides
    fun provideRestAreaRoomRemoteDataSource(restAreaService: RestAreaService):RestAreaRoomRemoteDataSource{
        return RestAreaRoomRemoteDataSourceImpl(restAreaService)
    }

    @Singleton
    @Provides
    fun provideRestAreaFoodRemoteDataSource(restAreaService: RestAreaService):RestAreaFoodRemoteDataSource{
        return RestAreaFoodRemoteDataSourceImpl(restAreaService)
    }

    @Singleton
    @Provides
    fun provideDirectionRemoteDataSource(restAreaService: RestAreaService):DirectionRemoteDataSource{
        return DirectionRemoteDataSourceImpl(restAreaService)
    }

    @Singleton
    @Provides
    fun provideSearchRemoteDataSource(restAreaService: RestAreaService):SearchRemoteDataSource{
        return SearchRemoteDataSourceImpl(restAreaService)
    }
}