package com.minjaee.restareaapp.data.repository.search.datasourceimpl

import com.minjaee.restareaapp.data.db.SearchLogDAO
import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap
import com.minjaee.restareaapp.data.repository.search.datasource.SearchLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchLocalDataSourceImpl(private val searchLogDAO: SearchLogDAO) : SearchLocalDataSource {
    override suspend fun getSearchFromDB(): List<SearchMap> {
        return searchLogDAO.getSearchLogs()
    }

    override suspend fun saveSearchToDB(searchLog: List<SearchMap>) {
        CoroutineScope(Dispatchers.IO).launch {
            searchLogDAO.saveSearch(searchLog)
        }
    }

    override suspend fun deleteOne(document: SearchMap) {
        CoroutineScope(Dispatchers.IO).launch {
            searchLogDAO.deleteOne(document)
        }
    }
}