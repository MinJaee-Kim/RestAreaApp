package com.minjaee.restareaapp.data.repository.restarea.datasource

import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import retrofit2.Response

interface RestAreaFoodRemoteDataSource {
    suspend fun getRestAreaFood(): Response<RestAreaFood>
}