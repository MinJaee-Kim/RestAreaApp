package com.minjaee.restareaapp.data.repository.direction.datasource

import com.minjaee.restareaapp.data.model.getdirection.GetDirections

interface DirectionCacheDataSource {
    suspend fun getDirectionFromCache():List<GetDirections>
    suspend fun saveDirectionFromCache(direction:List<GetDirections>)
}