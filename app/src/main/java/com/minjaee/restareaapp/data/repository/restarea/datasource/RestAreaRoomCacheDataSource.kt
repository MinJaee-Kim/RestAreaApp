package com.minjaee.restareaapp.data.repository.restarea.datasource

import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import com.minjaee.restareaapp.data.util.Resource

interface RestAreaRoomCacheDataSource {
    suspend fun getRestAreaRoomFromCache(): Resource<RestAreaRoom>
    suspend fun saveRestAreaRoomFromCache(restAreaRoom: Resource<RestAreaRoom>)
}