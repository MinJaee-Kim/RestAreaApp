package com.minjaee.restareaapp.data.repository.restarea.datasource

import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom

interface RestAreaRoomCacheDataSource {
    suspend fun getRestAreaRoomFromCache():List<RestAreaRoom>
    suspend fun saveRestAreaRoomFromCache(restAreaRoom: List<RestAreaRoom>)
}