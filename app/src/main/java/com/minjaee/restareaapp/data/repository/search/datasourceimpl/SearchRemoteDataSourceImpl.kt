package com.minjaee.restareaapp.data.repository.search.datasourceimpl

import androidx.room.Query
import com.minjaee.restareaapp.data.api.RestareaService
import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.data.repository.search.datasource.SearchRemoteDataSource
import retrofit2.Response

class SearchRemoteDataSourceImpl(private val restareaService: RestareaService) : SearchRemoteDataSource {
    override suspend fun getSearch(
        y : Double,
        x : Double,
        radius : Int,
        query: String
    ): Response<Document> = restareaService.getKeyWordSearch(y, x, radius, query)
}