package com.minjaee.restareaapp.data.repository.direction.datasourceimpl

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionCacheDataSource
import retrofit2.Response

class DirectionCacheDataSourceImpl : DirectionCacheDataSource {
    private lateinit var direction:Response<GetDirections>
    override suspend fun getDirectionFromCache(): Response<GetDirections> {
        return direction
    }

    override suspend fun saveDirectionFromCache(direction: Response<GetDirections>) {
        this.direction = direction
    }
}