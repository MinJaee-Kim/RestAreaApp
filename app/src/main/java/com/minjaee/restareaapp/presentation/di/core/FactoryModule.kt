package com.minjaee.restareaapp.presentation.di.core

import android.app.Application
import com.minjaee.restareaapp.domain.usecase.direction.GetDirectionUseCase
import com.minjaee.restareaapp.domain.usecase.restarea.GetRestAreaFoodUseCase
import com.minjaee.restareaapp.domain.usecase.restarea.GetRestAreaRoomUseCase
import com.minjaee.restareaapp.domain.usecase.search.GetNoLocationSearchAreaUseCase
import com.minjaee.restareaapp.domain.usecase.search.GetSearchAreaUseCase
import com.minjaee.restareaapp.presentation.viewmodel.DirectionViewModelFactory
import com.minjaee.restareaapp.presentation.viewmodel.RestAreaViewModelFactory
import com.minjaee.restareaapp.presentation.viewmodel.SearchViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideRestAreaViewModelFactory(
        application: Application,
        getRestAreaRoomUseCase: GetRestAreaRoomUseCase,
        getRestAreaFoodUseCase: GetRestAreaFoodUseCase
    ): RestAreaViewModelFactory{
        return RestAreaViewModelFactory(
            application,
            getRestAreaRoomUseCase,
            getRestAreaFoodUseCase
        )
    }

    @Singleton
    @Provides
    fun provideDirectionViewModelFactory(
        application: Application,
        getDirectionUseCase: GetDirectionUseCase
    ): DirectionViewModelFactory{
        return DirectionViewModelFactory(
            application,
            getDirectionUseCase
        )
    }

    @Singleton
    @Provides
    fun provideSearchViewModelFactory(
        application: Application,
        getSearchAreaUseCase: GetSearchAreaUseCase,
        getNoLocationSearchAreaUseCase: GetNoLocationSearchAreaUseCase
    ): SearchViewModelFactory{
        return SearchViewModelFactory(
            application,
            getSearchAreaUseCase,
            getNoLocationSearchAreaUseCase
        )
    }
}