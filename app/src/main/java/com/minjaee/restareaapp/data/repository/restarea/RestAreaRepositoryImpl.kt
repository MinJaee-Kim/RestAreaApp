package com.minjaee.restareaapp.data.repository.restarea

import android.util.Log
import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaFoodCacheDataSource
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaFoodRemoteDataSource
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaRoomCacheDataSource
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaRoomRemoteDataSource
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.domain.repository.RestAreaRepository
import retrofit2.Response
import java.lang.Exception

class RestAreaRepositoryImpl(
    private val restAreaFoodCacheDataSource: RestAreaFoodCacheDataSource,
    private val restAreaFoodRemoteDataSource: RestAreaFoodRemoteDataSource,
    private val restAreaRoomCacheDataSource: RestAreaRoomCacheDataSource,
    private val restAreaRoomRemoteDataSource: RestAreaRoomRemoteDataSource
) : RestAreaRepository {
    lateinit var restAreaFoodList: Resource<RestAreaFood>
    lateinit var restAreaRoomList: Resource<RestAreaRoom>
    override suspend fun getRestAreaRoom(serviceAreaName: String): Resource<RestAreaRoom> {
        return getRestAreaRoomFromCache(serviceAreaName)
    }

    override suspend fun getRestAreaFood(stdRestNm: String): Resource<RestAreaFood> {
        return getRestAreaFoodFormCache(stdRestNm)
    }

    private fun responseToRoomResource(response: Response<RestAreaRoom>): Resource<RestAreaRoom> {
        if (response.isSuccessful){
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responseToFoodResource(response: Response<RestAreaFood>): Resource<RestAreaFood> {
        if (response.isSuccessful){
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    suspend fun getRestAreaRoomFromCache(serviceAreaName: String) : Resource<RestAreaRoom> {
        try {
            restAreaRoomList = restAreaRoomCacheDataSource.getRestAreaRoomFromCache()
        } catch (exception: Exception){
            Log.i("TAG", exception.message.toString())
        }

        if (::restAreaRoomList.isInitialized){
            return restAreaRoomList
        } else {
            restAreaRoomList = getRestAreaRoomFromAPI(serviceAreaName)
            restAreaRoomCacheDataSource.saveRestAreaRoomFromCache(restAreaRoomList)
        }

        return restAreaRoomList
    }

    suspend fun getRestAreaRoomFromAPI(serviceAreaName: String) : Resource<RestAreaRoom> {
        return responseToRoomResource(
            restAreaRoomRemoteDataSource.getRestAreaRoom(serviceAreaName)
        )
    }

    suspend fun getRestAreaFoodFormCache(stdRestNm: String) : Resource<RestAreaFood> {
        try {
            restAreaFoodList = restAreaFoodCacheDataSource.getRestAreaFoodFromCache()
        } catch (exception: Exception){
            Log.i("TAG", exception.message.toString())
        }

        if (::restAreaFoodList.isInitialized){
            return restAreaFoodList
        } else {
            restAreaFoodList = getRestAreaFoodFromAPI(stdRestNm)
            restAreaFoodCacheDataSource.saveRestAreaFoodFromCache(restAreaFoodList)
        }

        return restAreaFoodList
    }

    suspend fun getRestAreaFoodFromAPI(stdRestNm: String) : Resource<RestAreaFood> {
        return responseToFoodResource(
            restAreaFoodRemoteDataSource.getRestAreaFood(stdRestNm)
        )
    }
}