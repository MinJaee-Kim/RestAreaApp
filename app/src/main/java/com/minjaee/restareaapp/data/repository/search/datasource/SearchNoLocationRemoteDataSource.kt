package com.minjaee.restareaapp.data.repository.search.datasource

import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap
import retrofit2.Response

interface SearchNoLocationRemoteDataSource {
    suspend fun getSearch(query: String): Response<SearchMap>
}