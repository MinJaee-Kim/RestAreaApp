package com.minjaee.restareaapp.data.repository.direction.datasourceimpl

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionCacheDataSource
import retrofit2.Response

class DirectionCacheDataSourceImpl : DirectionCacheDataSource {
    private lateinit var direction:GetDirections
    override suspend fun getDirectionFromCache(): GetDirections {
        return direction
    }

    override suspend fun saveDirectionFromCache(direction: GetDirections) {
        this.direction = direction
    }
}