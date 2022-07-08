package com.minjaee.restareaapp.data.api

import com.minjaee.restareaapp.BuildConfig
import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface DirectionService {
    @GET("map-direction/v1/driving")
    suspend fun getDirection(
        @Query("start") start: String,
        @Query("goal") goal: String,
        @Header("X-NCP-APIGW-API-KEY-ID") id: String = BuildConfig.NAVER_API_ID,
        @Header("X-NCP-APIGW-API-KEY") key: String = BuildConfig.NAVER_API_KEY
    ): Response<GetDirections>
}