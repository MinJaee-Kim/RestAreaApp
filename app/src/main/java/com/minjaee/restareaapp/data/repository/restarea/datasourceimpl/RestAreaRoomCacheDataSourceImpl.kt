package com.minjaee.restareaapp.data.repository.restarea.datasourceimpl

import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaRoomCacheDataSource

class RestAreaRoomCacheDataSourceImpl : RestAreaRoomCacheDataSource {
    override suspend fun getRestAreaRoomFromCache(): List<RestAreaRoom> {
        TODO("Not yet implemented")
    }

    override suspend fun saveRestAreaRoomFromCache(restAreaRoom: List<RestAreaRoom>) {
        TODO("Not yet implemented")
    }
}