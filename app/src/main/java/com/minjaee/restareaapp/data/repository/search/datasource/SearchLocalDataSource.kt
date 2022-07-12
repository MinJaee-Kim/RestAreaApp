package com.minjaee.restareaapp.data.repository.search.datasource

import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap

interface SearchLocalDataSource {
    suspend fun getSearchFromDB():List<SearchMap>
    suspend fun saveSearchToDB(searchLog: List<SearchMap>)
    suspend fun deleteOne(document: SearchMap)
}