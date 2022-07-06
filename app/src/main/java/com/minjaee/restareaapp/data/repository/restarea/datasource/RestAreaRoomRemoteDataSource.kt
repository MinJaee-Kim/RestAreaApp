package com.minjaee.restareaapp.data.repository.restarea.datasource

import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import retrofit2.Response

interface RestAreaRoomRemoteDataSource {
    suspend fun getRestAreaRoom(serviceAreaName : String): Response<RestAreaRoom>
}