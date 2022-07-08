package com.minjaee.restareaapp.domain.usecase.direction

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.model.getdirection.Goal
import com.minjaee.restareaapp.domain.repository.DirectionRepository
import retrofit2.Response

class GetDirectionUseCase(private val directionRepository: DirectionRepository) {
    suspend fun execute(start: String, goal: String): GetDirections = directionRepository.getDirection(start, goal)
}