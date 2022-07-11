package com.minjaee.restareaapp.data.repository.search.datasourceimpl

import com.minjaee.restareaapp.data.api.SearchService
import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.data.repository.search.datasource.SearchNoLocationRemoteDataSource
import retrofit2.Response

class SearchNoLocationRemoteDataSourceImpl(private val searchService: SearchService): SearchNoLocationRemoteDataSource {
    override suspend fun getSearch(query: String): Response<Document> = searchService.getKeyWordNoLocationSearch(query)
}