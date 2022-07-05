package com.minjaee.restareaapp.data.repository.search.datasourceimpl

import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.data.repository.search.datasource.SearchCacheDataSource

class SearchCacheDataSourceImpl : SearchCacheDataSource {
    override suspend fun getSearchFromCache(): List<Document> {
        TODO("Not yet implemented")
    }

    override suspend fun saveSearchFromCache(searchLog: List<Document>) {
        TODO("Not yet implemented")
    }
}