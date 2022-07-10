package com.minjaee.restareaapp.presentation.di.core

import android.app.Application
import com.minjaee.restareaapp.domain.usecase.direction.GetDirectionUseCase
import com.minjaee.restareaapp.domain.usecase.restarea.GetRestAreaFoodUseCase
import com.minjaee.restareaapp.domain.usecase.search.GetSearchAreaUseCase
import com.minjaee.restareaapp.presentation.viewmodel.RestAreaViewModelFactory
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
        getDirectionUseCase: GetDirectionUseCase,
        getRestAreaFoodUseCase: GetRestAreaFoodUseCase
    ): RestAreaViewModelFactory{
        return RestAreaViewModelFactory(
            application,
            getDirectionUseCase,
            getRestAreaFoodUseCase
        )
    }
}