package com.minjaee.restareaapp.domain.usecase.direction

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.domain.repository.DirectionRepository

class GetDirectionUseCase(private val directionRepository: DirectionRepository) {
    suspend fun execute(): List<GetDirections> = directionRepository.getDirection()
}