package com.minjaee.restareaapp.presentation.di.core

import com.google.gson.GsonBuilder
import com.minjaee.restareaapp.BuildConfig
import com.minjaee.restareaapp.data.api.DirectionService
import com.minjaee.restareaapp.data.api.RestAreaService
import com.minjaee.restareaapp.data.api.SearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Singleton
    @Provides
    @Named("RestArea")
    fun provideRestAreaRetrofit():Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.OPEN_API_URL)
            .build()
    }

    @Singleton
    @Provides
    @Named("Direction")
    fun provideDirectionRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://naveropenapi.apigw.ntruss.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Singleton
    @Provides
    @Named("Search")
    fun provideSearchRetrofit():Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.KAKAO_API_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideRestAreaService(@Named("RestArea") retrofit: Retrofit):RestAreaService{
        return retrofit.create(RestAreaService::class.java)
    }

    @Singleton
    @Provides
    fun provideDirectionService(@Named("Direction") retrofit: Retrofit):DirectionService{
        return retrofit.create(DirectionService::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchService(@Named("Search") retrofit: Retrofit):SearchService{
        return retrofit.create(SearchService::class.java)
    }
}