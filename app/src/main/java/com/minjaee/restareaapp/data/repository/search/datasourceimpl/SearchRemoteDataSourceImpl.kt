package com.minjaee.restareaapp.data.repository.search.datasourceimpl

import com.minjaee.restareaapp.data.api.SearchService
import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap
import com.minjaee.restareaapp.data.repository.search.datasource.SearchRemoteDataSource
import retrofit2.Response

class SearchRemoteDataSourceImpl(private val searchService: SearchService) : SearchRemoteDataSource {
    override suspend fun getSearch(
        y : Double,
        x : Double,
        radius : Int,
        query: String
    ): Response<SearchMap> = searchService.getKeyWordSearch(y, x, radius, query)
}