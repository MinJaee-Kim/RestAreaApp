package com.minjaee.restareaapp.presentation.di.core

import com.minjaee.restareaapp.BuildConfig
import com.minjaee.restareaapp.data.api.RestAreaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.OPEN_API_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideRestAreaService(retrofit: Retrofit):RestAreaService{
        return retrofit.create(RestAreaService::class.java)
    }
}