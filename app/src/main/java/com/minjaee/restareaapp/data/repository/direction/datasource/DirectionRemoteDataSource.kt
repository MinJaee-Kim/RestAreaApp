package com.minjaee.restareaapp.data.repository.direction.datasource

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.model.getdirection.Goal
import retrofit2.Response

interface DirectionRemoteDataSource {
    suspend fun getDirection(start: String, goal: String): Response<GetDirections>
}