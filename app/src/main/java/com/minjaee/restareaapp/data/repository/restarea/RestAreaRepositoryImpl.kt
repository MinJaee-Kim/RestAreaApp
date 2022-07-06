package com.minjaee.restareaapp.data.repository.restarea

import android.util.Log
import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaFoodCacheDataSource
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaFoodRemoteDataSource
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaRoomCacheDataSource
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaRoomRemoteDataSource
import com.minjaee.restareaapp.domain.repository.RestAreaRepository
import java.lang.Exception

class RestAreaRepositoryImpl(
    private val restAreaFoodCacheDataSource: RestAreaFoodCacheDataSource,
    private val restAreaFoodRemoteDataSource: RestAreaFoodRemoteDataSource,
    private val restAreaRoomCacheDataSource: RestAreaRoomCacheDataSource,
    private val restAreaRoomRemoteDataSource: RestAreaRoomRemoteDataSource
) : RestAreaRepository {
    override suspend fun getRestAreaRoom(serviceAreaName: String): List<RestAreaRoom> {
        return getRestAreaRoomFromCache(serviceAreaName)
    }

    override suspend fun getRestAreaFood(stdRestNm: String): List<RestAreaFood> {
        return getRestAreaFoodFormCache(stdRestNm)
    }

    suspend fun getRestAreaRoomFromCache(serviceAreaName: String) : List<RestAreaRoom> {
        lateinit var restAreaRoomList: List<RestAreaRoom>
        try {
            restAreaRoomList = restAreaRoomCacheDataSource.getRestAreaRoomFromCache()
        } catch (exception: Exception){
            Log.i("TAG", exception.message.toString())
        }

        if (restAreaRoomList.size>0){
            return restAreaRoomList
        } else {
            restAreaRoomList = getRestAreaRoomFromAPI(serviceAreaName)
            restAreaRoomCacheDataSource.saveRestAreaRoomFromCache(restAreaRoomList)
        }

        return restAreaRoomList
    }

    suspend fun getRestAreaRoomFromAPI(serviceAreaName: String) : List<RestAreaRoom> {
        lateinit var restAreaRoomList: List<RestAreaRoom>
        try {
            val response = restAreaRoomRemoteDataSource.getRestAreaRoom(serviceAreaName)
            val body = response.body()

            if (body!=null){
                restAreaRoomList = listOf(body)
            }

        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }
        return restAreaRoomList
    }

    suspend fun getRestAreaFoodFormCache(stdRestNm: String) : List<RestAreaFood> {
        lateinit var restAreaFoodList: List<RestAreaFood>
        try {
            restAreaFoodList = restAreaFoodCacheDataSource.getRestAreaFoodFromCache()
        } catch (exception: Exception){
            Log.i("TAG", exception.message.toString())
        }

        if (restAreaFoodList.size>0){
            return restAreaFoodList
        } else {
            restAreaFoodList = getRestAreaFoodFromAPI(stdRestNm)
            restAreaFoodCacheDataSource.saveRestAreaFoodFromCache(restAreaFoodList)
        }

        return restAreaFoodList
    }

    suspend fun getRestAreaFoodFromAPI(stdRestNm: String) : List<RestAreaFood> {
        lateinit var restAreaFoodList: List<RestAreaFood>
        try {
            val response = restAreaFoodRemoteDataSource.getRestAreaFood(stdRestNm)
            val body = response.body()

            if (body!=null){
                restAreaFoodList = listOf(body)
            }

        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }
        return restAreaFoodList
    }
}