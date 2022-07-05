package com.minjaee.restareaapp.data.repository.restarea.datasourceimpl

import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaFoodCacheDataSource

class RestAreaFoodCacheDataSourceImpl : RestAreaFoodCacheDataSource {
    override suspend fun getRestAreaFoodFromCache(): List<RestAreaFood> {
        TODO("Not yet implemented")
    }

    override suspend fun saveRestAreaFoodFromCache(restAreaFood: List<RestAreaFood>) {
        TODO("Not yet implemented")
    }
}