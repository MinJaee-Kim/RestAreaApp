package com.minjaee.restareaapp.domain.repository

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import retrofit2.Response

interface DirectionRepository {
    suspend fun getDirection(): Response<GetDirections>
}