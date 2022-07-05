package com.minjaee.restareaapp.data.repository.search.datasource

import com.minjaee.restareaapp.data.model.keywordsearch.Document

interface SearchCacheDataSource {
    suspend fun getSearchFromCache():List<Document>
    suspend fun saveSearchFromCache(searchLog: List<Document>)
}