package com.minjaee.restareaapp.data.repository.search.datasource

import com.minjaee.restareaapp.data.model.keywordsearch.Document
import retrofit2.Response

interface SearchRemoteDataSource {
    suspend fun getSearch(): Response<Document>
}