package com.minjaee.restareaapp.domain.usecase.direction

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.model.getdirection.Goal
import com.minjaee.restareaapp.domain.repository.DirectionRepository

class GetDirectionUseCase(private val directionRepository: DirectionRepository) {
    suspend fun execute(start: String, goal: String): List<GetDirections> = directionRepository.getDirection(start, goal)
}