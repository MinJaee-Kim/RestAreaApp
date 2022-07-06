package com.minjaee.restareaapp.data.repository.restarea.datasourceimpl

import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaRoomCacheDataSource

class RestAreaRoomCacheDataSourceImpl : RestAreaRoomCacheDataSource {
    private var restAreaRoomList = ArrayList<RestAreaRoom>()
    override suspend fun getRestAreaRoomFromCache(): List<RestAreaRoom> {
        return restAreaRoomList
    }

    override suspend fun saveRestAreaRoomFromCache(restAreaRoom: List<RestAreaRoom>) {
        restAreaRoomList.clear()
        restAreaRoomList = ArrayList(restAreaRoom)
    }
}