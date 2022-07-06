package com.minjaee.restareaapp.data.repository.search

import android.util.Log
import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.data.repository.search.datasource.SearchCacheDataSource
import com.minjaee.restareaapp.data.repository.search.datasource.SearchLocalDataSource
import com.minjaee.restareaapp.data.repository.search.datasource.SearchRemoteDataSource
import com.minjaee.restareaapp.domain.repository.SearchRepository
import java.lang.Exception

class SearchRepositoryImpl(
    private val searchCacheDataSource: SearchCacheDataSource,
    private val searchLocalDataSource: SearchLocalDataSource,
    private val searchRemoteDataSource: SearchRemoteDataSource
) : SearchRepository {
    override suspend fun getSearchArea(
        y: Double,
        x: Double,
        radius: Int,
        query: String
    ): List<Document> {
        return getSearchFromCache(y, x, radius, query)
    }

    override suspend fun saveSearchArea() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSearchArea(document: Document) {
        TODO("Not yet implemented")
    }

    suspend fun getSearchFromCache(
        y: Double,
        x: Double,
        radius: Int,
        query: String
    ): List<Document> {
        lateinit var searchList: List<Document>
        try {
            searchList = searchCacheDataSource.getSearchFromCache()
        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }

        if (searchList.size>0){
            return searchList
        } else {
            searchList = getSearchFromAPI(y, x, radius, query)
            searchCacheDataSource.saveSearchFromCache(searchList)
        }

        return searchList
    }

    suspend fun getSearchFromAPI(
        y: Double,
        x: Double,
        radius: Int,
        query: String
    ): List<Document> {
        lateinit var searchList: List<Document>
        try {
            val response = searchRemoteDataSource.getSearch(y, x, radius, query)
            val body = response.body()

            if (body!=null) {
                searchList = listOf(body)
            }

        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }

        return searchList
    }
}