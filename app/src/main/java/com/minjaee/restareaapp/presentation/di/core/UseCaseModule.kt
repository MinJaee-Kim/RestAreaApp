package com.minjaee.restareaapp.presentation.di.core

import com.minjaee.restareaapp.domain.repository.DirectionRepository
import com.minjaee.restareaapp.domain.repository.RestAreaRepository
import com.minjaee.restareaapp.domain.repository.SearchRepository
import com.minjaee.restareaapp.domain.usecase.direction.GetDirectionUseCase
import com.minjaee.restareaapp.domain.usecase.restarea.GetRestAreaFoodUseCase
import com.minjaee.restareaapp.domain.usecase.restarea.GetRestAreaRoomUseCase
import com.minjaee.restareaapp.domain.usecase.search.DeleteSavedSearchUseCase
import com.minjaee.restareaapp.domain.usecase.search.GetNoLocationSearchAreaUseCase
import com.minjaee.restareaapp.domain.usecase.search.GetSearchAreaUseCase
import com.minjaee.restareaapp.domain.usecase.search.SaveSearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetRestAreaRoomUseCase(
        restAreaRepository: RestAreaRepository
    ): GetRestAreaRoomUseCase{
        return GetRestAreaRoomUseCase(restAreaRepository)
    }

    @Singleton
    @Provides
    fun provideGetRestAreaFoodUseCase(
        restAreaRepository: RestAreaRepository
    ): GetRestAreaFoodUseCase {
        return GetRestAreaFoodUseCase(restAreaRepository)
    }

    @Singleton
    @Provides
    fun provideGetDirectionUseCase(
        directionRepository: DirectionRepository
    ): GetDirectionUseCase {
        return GetDirectionUseCase(directionRepository)
    }

    @Singleton
    @Provides
    fun provideGetSearchAreaUseCase(
        searchRepository: SearchRepository
    ): GetSearchAreaUseCase{
        return GetSearchAreaUseCase(searchRepository)
    }

    @Singleton
    @Provides
    fun provideGetNoLocationSearchAreaUseCase(
        searchRepository: SearchRepository
    ): GetNoLocationSearchAreaUseCase{
        return GetNoLocationSearchAreaUseCase(searchRepository)
    }

    @Singleton
    @Provides
    fun provideSaveSearchUserCase(
        searchRepository: SearchRepository
    ): SaveSearchUseCase{
        return SaveSearchUseCase(searchRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteSavedSearchUseCase(
        searchRepository: SearchRepository
    ): DeleteSavedSearchUseCase{
        return DeleteSavedSearchUseCase(searchRepository)
    }
}