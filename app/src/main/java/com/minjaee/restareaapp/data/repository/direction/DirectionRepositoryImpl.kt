package com.minjaee.restareaapp.data.repository.direction

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.domain.repository.DirectionRepository
import retrofit2.Response

class DirectionRepositoryImpl : DirectionRepository {
    override suspend fun getDirection(): Response<GetDirections> {
        TODO("Not yet implemented")
    }
}