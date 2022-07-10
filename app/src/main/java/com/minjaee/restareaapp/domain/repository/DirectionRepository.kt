package com.minjaee.restareaapp.domain.repository

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.util.Resource
import retrofit2.Response

interface DirectionRepository {
    suspend fun getDirection(start:String, goal:String): Resource<GetDirections>
}