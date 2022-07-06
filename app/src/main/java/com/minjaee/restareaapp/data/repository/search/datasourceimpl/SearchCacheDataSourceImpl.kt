package com.minjaee.restareaapp.data.repository.search.datasourceimpl

import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.data.repository.search.datasource.SearchCacheDataSource

class SearchCacheDataSourceImpl : SearchCacheDataSource {
    private var searchList = ArrayList<Document>()
    override suspend fun getSearchFromCache(): List<Document> {
        return searchList
    }

    override suspend fun saveSearchFromCache(searchLog: List<Document>) {
        searchList.clear()
        searchList = ArrayList(searchLog)
    }
}