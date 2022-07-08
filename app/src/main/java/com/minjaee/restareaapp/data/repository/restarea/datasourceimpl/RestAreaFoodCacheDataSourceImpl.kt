package com.minjaee.restareaapp.data.repository.restarea.datasourceimpl

import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaFoodCacheDataSource
import com.minjaee.restareaapp.data.util.Resource
import retrofit2.Response

class RestAreaFoodCacheDataSourceImpl : RestAreaFoodCacheDataSource {
    private lateinit var restAreaFoodList: Resource<RestAreaFood>
    override suspend fun getRestAreaFoodFromCache(): Resource<RestAreaFood> {
        return restAreaFoodList
    }

    override suspend fun saveRestAreaFoodFromCache(restAreaFood: Resource<RestAreaFood>) {
        restAreaFoodList = restAreaFood
    }
}