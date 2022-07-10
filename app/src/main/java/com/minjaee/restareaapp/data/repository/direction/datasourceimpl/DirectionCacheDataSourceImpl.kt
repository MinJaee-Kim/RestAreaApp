package com.minjaee.restareaapp.data.repository.direction.datasourceimpl

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionCacheDataSource
import com.minjaee.restareaapp.data.util.Resource
import retrofit2.Response

class DirectionCacheDataSourceImpl : DirectionCacheDataSource {
    private lateinit var directionList:Resource<GetDirections>
    override suspend fun getDirectionFromCache(): Resource<GetDirections> {
        return directionList
    }

    override suspend fun saveDirectionFromCache(direction: Resource<GetDirections>) {
        directionList = direction
    }
}