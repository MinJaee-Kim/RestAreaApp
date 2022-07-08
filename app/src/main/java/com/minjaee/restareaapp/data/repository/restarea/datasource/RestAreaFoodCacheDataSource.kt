package com.minjaee.restareaapp.data.repository.restarea.datasource

import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.util.Resource

interface RestAreaFoodCacheDataSource {
    suspend fun getRestAreaFoodFromCache(): Resource<RestAreaFood>
    suspend fun saveRestAreaFoodFromCache(restAreaFood: Resource<RestAreaFood>)
}