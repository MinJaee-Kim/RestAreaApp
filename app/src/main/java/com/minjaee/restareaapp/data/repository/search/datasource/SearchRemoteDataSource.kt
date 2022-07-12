package com.minjaee.restareaapp.data.repository.search.datasource

import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap
import retrofit2.Response

interface SearchRemoteDataSource {
    suspend fun getSearch(y : Double, x : Double, radius : Int, query: String): Response<SearchMap>
}