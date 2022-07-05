package com.minjaee.restareaapp.data.repository.search.datasourceimpl

import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.data.repository.search.datasource.SearchLocalDataSource

class SearchLocalDataSourceImpl : SearchLocalDataSource {
    override suspend fun getSearchFromDB(): List<Document> {
        TODO("Not yet implemented")
    }

    override suspend fun saveSearchToDB(searchLog: List<Document>) {
        TODO("Not yet implemented")
    }

    override suspend fun clearAll() {
        TODO("Not yet implemented")
    }
}