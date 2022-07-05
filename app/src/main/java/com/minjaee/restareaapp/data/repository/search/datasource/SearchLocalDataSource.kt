package com.minjaee.restareaapp.data.repository.search.datasource

import com.minjaee.restareaapp.data.model.keywordsearch.Document

interface SearchLocalDataSource {
    suspend fun getSearchFromDB():List<Document>
    suspend fun saveSearchToDB(searchLog: List<Document>)
    suspend fun clearAll()
}