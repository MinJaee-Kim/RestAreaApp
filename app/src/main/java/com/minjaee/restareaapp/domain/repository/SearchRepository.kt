package com.minjaee.restareaapp.domain.repository

import com.minjaee.restareaapp.data.model.keywordsearch.Document
import retrofit2.Response

interface SearchRepository {
    suspend fun getSearchArea(): Response<Document>?
    suspend fun saveSearchArea()
    suspend fun deleteSearchArea(document: Document)
}