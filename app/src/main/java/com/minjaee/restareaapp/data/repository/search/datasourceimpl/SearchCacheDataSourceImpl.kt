package com.minjaee.restareaapp.data.repository.search.datasourceimpl

import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.data.repository.search.datasource.SearchCacheDataSource
import com.minjaee.restareaapp.data.util.Resource

class SearchCacheDataSourceImpl : SearchCacheDataSource {
    private lateinit var searchList: Resource<Document>
    override suspend fun getSearchFromCache(): Resource<Document> {
        return searchList
    }

    override suspend fun saveSearchFromCache(searchLog: Resource<Document>) {
        searchList = searchLog
    }
}