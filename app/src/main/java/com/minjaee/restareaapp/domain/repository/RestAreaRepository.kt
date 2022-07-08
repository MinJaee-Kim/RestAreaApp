package com.minjaee.restareaapp.domain.repository

import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import com.minjaee.restareaapp.data.util.Resource

interface RestAreaRepository {
    suspend fun getRestAreaRoom(serviceAreaName: String): Resource<RestAreaRoom>
    suspend fun getRestAreaFood(stdRestNm: String):Resource<RestAreaFood>
}