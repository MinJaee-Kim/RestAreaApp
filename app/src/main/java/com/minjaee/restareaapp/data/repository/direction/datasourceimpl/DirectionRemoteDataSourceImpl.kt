package com.minjaee.restareaapp.data.repository.direction.datasourceimpl

import com.minjaee.restareaapp.data.api.DirectionService
import com.minjaee.restareaapp.data.api.RestAreaService
import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionRemoteDataSource
import retrofit2.Response

class DirectionRemoteDataSourceImpl(
    private val directionService: DirectionService
) : DirectionRemoteDataSource {
    override suspend fun getDirection(start: String, goal: String): Response<GetDirections> = directionService.getDirection(start, goal)
}