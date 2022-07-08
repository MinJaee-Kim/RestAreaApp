package com.minjaee.restareaapp.data.repository.direction.datasource

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import retrofit2.Response

interface DirectionCacheDataSource {
    suspend fun getDirectionFromCache(): GetDirections
    suspend fun saveDirectionFromCache(direction:GetDirections)
}