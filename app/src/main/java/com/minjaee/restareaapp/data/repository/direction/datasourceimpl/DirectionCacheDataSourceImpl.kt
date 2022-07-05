package com.minjaee.restareaapp.data.repository.direction.datasourceimpl

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionCacheDataSource

class DirectionCacheDataSourceImpl : DirectionCacheDataSource {
    override suspend fun getDirectionFromCache(): List<GetDirections> {
        TODO("Not yet implemented")
    }

    override suspend fun saveDirectionFromCache(direction: List<GetDirections>) {
        TODO("Not yet implemented")
    }
}