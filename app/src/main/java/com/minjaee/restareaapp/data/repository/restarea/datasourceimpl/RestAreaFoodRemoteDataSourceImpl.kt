package com.minjaee.restareaapp.data.repository.restarea.datasourceimpl

import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.repository.restarea.datasource.RestAreaFoodRemoteDataSource
import retrofit2.Response

class RestAreaFoodRemoteDataSourceImpl : RestAreaFoodRemoteDataSource {
    override suspend fun getRestAreaFood(): Response<RestAreaFood> {
        TODO("Not yet implemented")
    }
}