package com.minjaee.restareaapp.domain.repository

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.model.getdirection.Goal
import retrofit2.Response

interface DirectionRepository {
    suspend fun getDirection(start:String, goal:String): List<GetDirections>
}