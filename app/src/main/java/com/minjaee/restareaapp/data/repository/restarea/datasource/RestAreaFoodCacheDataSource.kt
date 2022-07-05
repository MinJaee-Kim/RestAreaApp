package com.minjaee.restareaapp.data.repository.restarea.datasource

import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood

interface RestAreaFoodCacheDataSource {
    suspend fun getRestAreaFoodFromCache():List<RestAreaFood>
    suspend fun saveRestAreaFoodFromCache(restAreaFood: List<RestAreaFood>)
}