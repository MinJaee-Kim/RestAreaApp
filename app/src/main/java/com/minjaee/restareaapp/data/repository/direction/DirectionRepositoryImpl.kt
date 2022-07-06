package com.minjaee.restareaapp.data.repository.direction

import android.util.Log
import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionCacheDataSource
import com.minjaee.restareaapp.data.repository.direction.datasource.DirectionRemoteDataSource
import com.minjaee.restareaapp.domain.repository.DirectionRepository
import java.lang.Exception

class DirectionRepositoryImpl(
    private val directionCacheDataSource: DirectionCacheDataSource,
    private val directionRemoteDataSource: DirectionRemoteDataSource
) : DirectionRepository {

    override suspend fun getDirection(
        start: String,
        goal: String
    ): List<GetDirections> {
        return getDirectionFromCache(start, goal)
    }

    suspend fun getDirectionFromCache(
        start: String,
        goal: String
    ) : List<GetDirections> {
        lateinit var directionList: List<GetDirections>
        try {
            directionList = directionCacheDataSource.getDirectionFromCache()
        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }

        if (directionList.size>0){
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
    ) : List<GetDirections> {
        lateinit var directionList: List<GetDirections>

        try {
            val response = directionRemoteDataSource.getDirection(start, goal)
            val body = response.body()

            if (body!=null) {
                directionList = listOf(body)
            }
        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }

        return directionList
    }
}