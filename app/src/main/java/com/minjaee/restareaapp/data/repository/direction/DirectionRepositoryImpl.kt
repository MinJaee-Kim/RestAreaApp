package com.minjaee.restareaapp.data.repository.direction

import android.util.Log
import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionCacheDataSource
import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionRemoteDataSource
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.domain.repository.DirectionRepository
import retrofit2.Response
import java.lang.Exception

class DirectionRepositoryImpl(
    private val directionCacheDataSource: DirectionCacheDataSource,
    private val directionRemoteDataSource: DirectionRemoteDataSource
) : DirectionRepository {
    lateinit var directionList: Resource<GetDirections>
    override suspend fun getDirection(
        start: String,
        goal: String
    ): Resource<GetDirections> {
        return getDirectionFromCache(start, goal)
    }

    private fun responseToDirectionResource(response: Response<GetDirections>): Resource<GetDirections> {
        if (response.isSuccessful){
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    suspend fun getDirectionFromCache(
        start: String,
        goal: String
    ) : Resource<GetDirections> {
        try {
            directionList = directionCacheDataSource.getDirectionFromCache()
        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }

        if (::directionList.isInitialized){
            return directionList
        } else {
            directionList = getDirectionFromAPI(start, goal)
            directionCacheDataSource.saveDirectionFromCache(directionList)
        }

        return directionList
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