package com.minjaee.restareaapp.data.repository.direction.datasourceimpl

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionRemoteDataSource
import retrofit2.Response

class DirectionRemoteDataSourceImpl : DirectionRemoteDataSource {
    override suspend fun getDirection(): Response<GetDirections> {
        TODO("Not yet implemented")
    }
}