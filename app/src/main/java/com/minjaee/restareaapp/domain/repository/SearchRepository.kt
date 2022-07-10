package com.minjaee.restareaapp.domain.repository

import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.data.util.Resource

interface SearchRepository {
    suspend fun getSearchArea(y: Double, x: Double, radius: Int, query: String): Resource<Document>
    suspend fun saveSearchArea()
    suspend fun deleteSearchArea(document: Document)
}