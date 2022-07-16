package com.minjaee.restareaapp.data.repository.restarea

import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaFoodRemoteDataSource
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaRoomRemoteDataSource
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.domain.repository.RestAreaRepository
import retrofit2.Response

class RestAreaRepositoryImpl(
    private val restAreaFoodRemoteDataSource: RestAreaFoodRemoteDataSource,
    private val restAreaRoomRemoteDataSource: RestAreaRoomRemoteDataSource
) : RestAreaRepository {
    override suspend fun getRestAreaRoom(serviceAreaName: String): Resource<RestAreaRoom> {
        return getRestAreaRoomFromAPI(serviceAreaName)
    }

    override suspend fun getRestAreaFood(stdRestNm: String): Resource<RestAreaFood> {
        return getRestAreaFoodFromAPI(stdRestNm)
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


    suspend fun getRestAreaRoomFromAPI(serviceAreaName: String) : Resource<RestAreaRoom> {
        return responseToRoomResource(
            restAreaRoomRemoteDataSource.getRestAreaRoom(serviceAreaName)
        )
    }

    suspend fun getRestAreaFoodFromAPI(stdRestNm: String) : Resource<RestAreaFood> {
        return responseToFoodResource(
            restAreaFoodRemoteDataSource.getRestAreaFood(stdRestNm)
        )
    }
}