package com.minjaee.restareaapp.data.repository.direction.datasource

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.util.Resource
import retrofit2.Response

interface DirectionCacheDataSource {
    suspend fun getDirectionFromCache(): Resource<GetDirections>
    suspend fun saveDirectionFromCache(direction:Resource<GetDirections>)
}