package com.minjaee.restareaapp.data.api

import com.minjaee.restareaapp.BuildConfig
import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchService {
    @GET("v2/local/search/keyword.json")
    suspend fun getKeyWordSearch(
        @Query("y") y: Double,
        @Query("x") x:Double,
        @Query("radius") radius:Int,
        @Query("query") query: String = "휴게소",
        @Header("Authorization") key: String = BuildConfig.KAKAO_API_KEY
    ): Response<SearchMap>

    @GET("v2/local/search/keyword.json")
    suspend fun getKeyWordNoLocationSearch(
        @Query("query") query: String,
        @Header("Authorization") key: String = BuildConfig.KAKAO_API_KEY
    ): Response<SearchMap>
}