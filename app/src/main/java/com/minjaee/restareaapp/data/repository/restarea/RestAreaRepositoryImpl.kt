package com.minjaee.restareaapp.data.repository.restarea

import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import com.minjaee.restareaapp.domain.repository.RestAreaRepository

class RestAreaRepositoryImpl : RestAreaRepository {
    override suspend fun getRestAreaRoom(): List<RestAreaRoom>? {
        TODO("Not yet implemented")
    }

    override suspend fun getRestAreaFood(): List<RestAreaFood>? {
        TODO("Not yet implemented")
    }
}