package com.minjaee.restareaapp.data.repository.restarea.datasourceimpl

import com.minjaee.restareaapp.data.api.RestAreaService
import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaFoodRemoteDataSource
import retrofit2.Response

class RestAreaFoodRemoteDataSourceImpl(private val restAreaService: RestAreaService) : RestAreaFoodRemoteDataSource {
    override suspend fun getRestAreaFood(stdRestNm: String): Response<RestAreaFood> = restAreaService.getRestAreaFood(stdRestNm)
}