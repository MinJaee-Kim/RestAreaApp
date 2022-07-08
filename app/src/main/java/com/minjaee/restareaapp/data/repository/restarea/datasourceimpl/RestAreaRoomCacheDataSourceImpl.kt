package com.minjaee.restareaapp.data.repository.restarea.datasourceimpl

import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaRoomCacheDataSource
import com.minjaee.restareaapp.data.util.Resource

class RestAreaRoomCacheDataSourceImpl : RestAreaRoomCacheDataSource {
    private lateinit var restAreaRoomList: Resource<RestAreaRoom>
    override suspend fun getRestAreaRoomFromCache(): Resource<RestAreaRoom> {
        return restAreaRoomList
    }

    override suspend fun saveRestAreaRoomFromCache(restAreaRoom: Resource<RestAreaRoom>) {
        restAreaRoomList = restAreaRoom
    }
}