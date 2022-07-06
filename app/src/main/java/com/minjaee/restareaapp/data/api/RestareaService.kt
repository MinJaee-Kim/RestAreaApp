package com.minjaee.restareaapp.data.api

import com.minjaee.restareaapp.BuildConfig
import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RestareaService {
    @GET("/v2/local/search/keyword.json")
    suspend fun getKeyWordSearch(
        @Query("y") y: Double,
        @Query("x") x:Double,
        @Query("radius") radius:Int,
        @Query("query") query: String = "휴게소",
        @Header("Authorization") key: String = BuildConfig.KAKAO_API_KEY
    ): Response<Document>

    @GET("map-direction/v1/driving")
    suspend fun getDirection(
        @Query("start") start: String,
        @Query("goal") goal: String,
        @Header("X-NCP-APIGW-API-KEY-ID") id: String = BuildConfig.NAVER_API_ID,
        @Header("X-NCP-APIGW-API-KEY") key: String = BuildConfig.NAVER_API_KEY
    ): Response<GetDirections>

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