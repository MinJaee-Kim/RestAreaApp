package com.minjaee.restareaapp.data.repository.direction

import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionRemoteDataSource
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.domain.repository.DirectionRepository
import retrofit2.Response

class DirectionRepositoryImpl(
    private val directionRemoteDataSource: DirectionRemoteDataSource
) : DirectionRepository {
    override suspend fun getDirection(
        start: String,
        goal: String
    ): Resource<GetDirections> {
        return getDirectionFromAPI(start, goal)
    }

    private fun responseToDirectionResource(response: Response<GetDirections>): Resource<GetDirections> {
        if (response.isSuccessful){
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    suspend fun getDirectionFromAPI(
        start: String,
        goal: String
    ) : Resource<GetDirections> {
        return responseToDirectionResource(
            directionRemoteDataSource.getDirection(start, goal)
        )
    }
}