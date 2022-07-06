package com.minjaee.restareaapp.domain.repository

import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom

interface RestAreaRepository {
    suspend fun getRestAreaRoom(serviceAreaName: String):List<RestAreaRoom>
    suspend fun getRestAreaFood(stdRestNm: String):List<RestAreaFood>
}