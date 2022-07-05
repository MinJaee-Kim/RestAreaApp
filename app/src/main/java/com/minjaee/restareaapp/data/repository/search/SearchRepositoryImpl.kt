package com.minjaee.restareaapp.data.repository.search

import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.domain.repository.SearchRepository
import retrofit2.Response

class SearchRepositoryImpl : SearchRepository {
    override suspend fun getSearchArea(): Response<Document>? {
        TODO("Not yet implemented")
    }

    override suspend fun saveSearchArea() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSearchArea(document: Document) {
        TODO("Not yet implemented")
    }
}