package com.minjaee.restareaapp.data.api

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RestareaService {
    @GET("/v2/local/search/keyword.json")
    suspend fun getKeyWordSearch(
        @Header("Authorization") key: String,
        @Query("y") y: Double,
        @Query("x") x:Double,
        @Query("radius") radius:Int,
        @Query("query") query: String
    ): Response<Document>

    @GET("map-direction/v1/driving")
    suspend fun getDirection(
        @Header("X-NCP-APIGW-API-KEY-ID") id: String,
        @Header("X-NCP-APIGW-API-KEY") key: String,
        @Query("start") start: String,
        @Query("goal") goal: String
    ): Response<GetDirections>

    @GET("business/conveniServiceArea")
    suspend fun getRestAreaRoom(
        @Query("key") key: String,
        @Query("type") type: String,
        @Query("serviceAreaName") serviceAreaName: String
    ): Response<RestAreaFood>

    @GET("restinfo/restBestfoodList")
    suspend fun getRestAreaFood(
        @Query("key") key: String,
        @Query("type") type: String,
        @Query("stdRestNm") stdRestNm: String
    ): Response<RestAreaFood>
}