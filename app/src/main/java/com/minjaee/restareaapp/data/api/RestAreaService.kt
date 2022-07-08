package com.minjaee.restareaapp.data.api

import com.minjaee.restareaapp.BuildConfig
import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestAreaService {
    @GET("business/conveniServiceArea")
    suspend fun getRestAreaRoom(
        @Query("serviceAreaName") serviceAreaName: String,
        @Query("type") type: String = "json",
        @Query("key") key: String = BuildConfig.OPEN_API_KEY
    ): Response<RestAreaRoom>

    @GET("restinfo/restBestfoodList")
    suspend fun getRestAreaFood(
        @Query("stdRestNm") stdRestNm: String,
        @Query("type") type: String = "json",
        @Query("key") key: String = BuildConfig.OPEN_API_KEY
    ): Response<RestAreaFood>
}