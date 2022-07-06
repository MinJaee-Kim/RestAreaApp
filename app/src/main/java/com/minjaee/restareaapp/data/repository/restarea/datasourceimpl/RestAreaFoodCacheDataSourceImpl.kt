package com.minjaee.restareaapp.data.repository.restarea.datasourceimpl

import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaFoodCacheDataSource

class RestAreaFoodCacheDataSourceImpl : RestAreaFoodCacheDataSource {
    private var restAreaFoodList = ArrayList<RestAreaFood>()
    override suspend fun getRestAreaFoodFromCache(): List<RestAreaFood> {
        return restAreaFoodList
    }

    override suspend fun saveRestAreaFoodFromCache(restAreaFood: List<RestAreaFood>) {
        restAreaFoodList.clear()
        restAreaFoodList = ArrayList(restAreaFood)
    }
}