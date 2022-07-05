package com.minjaee.restareaapp.domain.usecase.direction

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.domain.repository.DirectionRepository
import retrofit2.Response

class GetDirectionUseCase(private val directionRepository: DirectionRepository) {
    suspend fun execute(): Response<GetDirections> = directionRepository.getDirection()
}