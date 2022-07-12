package com.minjaee.restareaapp.data.repository.search

import android.util.Log
import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap
import com.minjaee.restareaapp.data.repository.search.datasource.SearchCacheDataSource
import com.minjaee.restareaapp.data.repository.search.datasource.SearchLocalDataSource
import com.minjaee.restareaapp.data.repository.search.datasource.SearchNoLocationRemoteDataSource
import com.minjaee.restareaapp.data.repository.search.datasource.SearchRemoteDataSource
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.domain.repository.SearchRepository
import retrofit2.Response
import java.lang.Exception

class SearchRepositoryImpl(
    private val searchCacheDataSource: SearchCacheDataSource,
    private val searchLocalDataSource: SearchLocalDataSource,
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val searchNoLocationRemoteDataSource: SearchNoLocationRemoteDataSource
) : SearchRepository {
    lateinit var searchList: Resource<SearchMap>

    override suspend fun getSearchArea(
        y: Double,
        x: Double,
        radius: Int,
        query: String
    ): Resource<SearchMap> {
        return getSearchFromCache(y, x, radius, query)
    }

    override suspend fun saveSearchArea() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSearchArea(document: SearchMap) {
        TODO("Not yet implemented")
    }

    override suspend fun getNoLocationSearchArea(query: String): Resource<SearchMap> {
        return getNoLocationSearchFromAPI(query)
    }

    private fun responseToSearchResource(response: Response<SearchMap>): Resource<SearchMap> {
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
    ): Resource<SearchMap> {
        try {
            searchList = searchCacheDataSource.getSearchFromCache()
        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }

        if (::searchList.isInitialized){
            searchList = getSearchFromAPI(y, x, radius, query)
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
    ): Resource<SearchMap> {
        return responseToSearchResource(
            searchRemoteDataSource.getSearch(y, x, radius, query)
        )
    }

    suspend fun getNoLocationSearchFromAPI(
        query: String
    ): Resource<SearchMap> {
        return responseToSearchResource(
            searchNoLocationRemoteDataSource.getSearch(query)
        )
    }
}