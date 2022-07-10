package com.minjaee.restareaapp.data.repository.search

import android.util.Log
import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.data.repository.search.datasource.SearchCacheDataSource
import com.minjaee.restareaapp.data.repository.search.datasource.SearchLocalDataSource
import com.minjaee.restareaapp.data.repository.search.datasource.SearchRemoteDataSource
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.domain.repository.SearchRepository
import retrofit2.Response
import java.lang.Exception

class SearchRepositoryImpl(
    private val searchCacheDataSource: SearchCacheDataSource,
    private val searchLocalDataSource: SearchLocalDataSource,
    private val searchRemoteDataSource: SearchRemoteDataSource
) : SearchRepository {
    lateinit var searchList: Resource<Document>

    override suspend fun getSearchArea(
        y: Double,
        x: Double,
        radius: Int,
        query: String
    ): Resource<Document> {
        return getSearchFromCache(y, x, radius, query)
    }

    override suspend fun saveSearchArea() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSearchArea(document: Document) {
        TODO("Not yet implemented")
    }

    private fun responseToSearchResource(response: Response<Document>): Resource<Document> {
        if (response.isSuccessful){
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    suspend fun getSearchFromCache(
        y: Double,
        x: Double,
        radius: Int,
        query: String
    ): Resource<Document> {
        try {
            searchList = searchCacheDataSource.getSearchFromCache()
        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }

        if (::searchList.isInitialized){
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
    ): Resource<Document> {
        return responseToSearchResource(
            searchRemoteDataSource.getSearch(y, x, radius, query)
        )

    }
}