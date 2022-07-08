package com.minjaee.restareaapp.domain.usecase.restarea

import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.domain.repository.RestAreaRepository

class GetRestAreaRoomUseCase(private val restAreaRepository: RestAreaRepository) {
    suspend fun execute(serviceAreaName: String): Resource<RestAreaRoom> = restAreaRepository.getRestAreaRoom(serviceAreaName)
}