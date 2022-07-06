package com.minjaee.restareaapp.data.repository.restarea.datasourceimpl

import com.minjaee.restareaapp.data.api.RestareaService
import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaRoomRemoteDataSource
import retrofit2.Response

class RestAreaRoomRemoteDataSourceImpl(private val restareaService: RestareaService) : RestAreaRoomRemoteDataSource {
    override suspend fun getRestAreaRoom(serviceAreaName : String): Response<RestAreaRoom> = restareaService.getRestAreaRoom(serviceAreaName)
}