package com.minjaee.restareaapp.data.repository.search.datasourceimpl

import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.data.repository.search.datasource.SearchRemoteDataSource
import retrofit2.Response

class SearchRemoteDataSourceImpl : SearchRemoteDataSource {
    override suspend fun getSearch(): Response<Document> {
        TODO("Not yet implemented")
    }
}