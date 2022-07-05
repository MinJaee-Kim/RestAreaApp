package com.minjaee.restareaapp.data.repository.restarea.datasourceimpl

import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaRoomRemoteDataSource
import retrofit2.Response

class RestAreaRoomRemoteDataSourceImpl : RestAreaRoomRemoteDataSource {
    override suspend fun getRestAreaRoom(): Response<RestAreaRoom> {
        TODO("Not yet implemented")
    }
}