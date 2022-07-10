package com.minjaee.restareaapp.data.repository.search.datasource

import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.data.util.Resource

interface SearchCacheDataSource {
    suspend fun getSearchFromCache(): Resource<Document>
    suspend fun saveSearchFromCache(searchLog: Resource<Document>)
}