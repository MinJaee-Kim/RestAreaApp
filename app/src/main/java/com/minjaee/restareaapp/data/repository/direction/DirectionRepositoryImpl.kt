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
    ): Response<GetDirections> {
        return getDirectionFromCache(start, goal)
    }

    suspend fun getDirectionFromCache(
        start: String,
        goal: String
    ) : Response<GetDirections> {
        lateinit var direction: Response<GetDirections>
        try {
            direction = directionCacheDataSource.getDirectionFromCache()
        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }

        if (direction.isSuccessful){
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
    ) : Response<GetDirections> {
        lateinit var direction: Response<GetDirections>

        try {
            val response = directionRemoteDataSource.getDirection(start, goal)
//            val body = response.body()

            if (direction.isSuccessful) {
                direction = response
            }
        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }

        return direction
    }
}