package com.minjaee.restareaapp.data.repository.direction

import android.util.Log
import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionCacheDataSource
import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionRemoteDataSource
import com.minjaee.restareaapp.domain.repository.DirectionRepository
import retrofit2.Response
import java.lang.Exception

class DirectionRepositoryImpl(
    private val directionCacheDataSource: DirectionCacheDataSource,
    private val directionRemoteDataSource: DirectionRemoteDataSource
) : DirectionRepository {

    override suspend fun getDirection(
        start: String,
        goal: String
    ): GetDirections {
        return getDirectionFromCache(start, goal)
    }

    suspend fun getDirectionFromCache(
        start: String,
        goal: String
    ) : GetDirections {
        lateinit var direction: GetDirections
        try {
            direction = directionCacheDataSource.getDirectionFromCache()
        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }

        if (false){
            return direction
        } else {
            direction = getDirectionFromAPI(start, goal)
            directionCacheDataSource.saveDirectionFromCache(direction)
        }

        return direction
    }

    suspend fun getDirectionFromAPI(
        start: String,
        goal: String
    ) : GetDirections {
        lateinit var direction: GetDirections

        try {
            val response = directionRemoteDataSource.getDirection(start, goal)
            val body = response.body()
            if (body != null) {
                direction = body
            }
        } catch (exception: Exception) {
            Log.i("dr", exception.message.toString())
        }

        return direction
    }
}