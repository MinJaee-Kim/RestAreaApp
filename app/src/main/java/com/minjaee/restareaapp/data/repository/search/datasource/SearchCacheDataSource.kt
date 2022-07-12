package com.minjaee.restareaapp.data.repository.search.datasource

import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap
import com.minjaee.restareaapp.data.util.Resource

interface SearchCacheDataSource {
    suspend fun getSearchFromCache(): Resource<SearchMap>
    suspend fun saveSearchFromCache(searchLog: Resource<SearchMap>)
}