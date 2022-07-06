package com.minjaee.restareaapp.domain.usecase.restarea

import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.domain.repository.RestAreaRepository
import retrofit2.Response

class GetRestAreaFoodUseCase(private val restAreaRepository: RestAreaRepository) {
    suspend fun execute(stdRestNm: String): List<RestAreaFood>? {
        return restAreaRepository.getRestAreaFood(stdRestNm)
    }
}