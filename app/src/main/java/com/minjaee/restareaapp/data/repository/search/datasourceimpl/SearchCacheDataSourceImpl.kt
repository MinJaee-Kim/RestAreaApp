package com.minjaee.restareaapp.data.repository.search.datasourceimpl

import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap
import com.minjaee.restareaapp.data.repository.search.datasource.SearchCacheDataSource
import com.minjaee.restareaapp.data.util.Resource

class SearchCacheDataSourceImpl : SearchCacheDataSource {
    private lateinit var searchList: Resource<SearchMap>
    override suspend fun getSearchFromCache(): Resource<SearchMap> {
        return searchList
    }

    override suspend fun saveSearchFromCache(searchLog: Resource<SearchMap>) {
        searchList = searchLog
    }
}