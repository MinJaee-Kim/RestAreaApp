package com.minjaee.restareaapp.data.repository.direction.datasourceimpl

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionCacheDataSource

class DirectionCacheDataSourceImpl : DirectionCacheDataSource {
    private var directionList = ArrayList<GetDirections>()
    override suspend fun getDirectionFromCache(): List<GetDirections> {
        return directionList
    }

    override suspend fun saveDirectionFromCache(direction: List<GetDirections>) {
        directionList.clear()
        directionList = ArrayList(direction)
    }
}