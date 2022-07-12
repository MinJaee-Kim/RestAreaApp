package com.minjaee.restareaapp.domain.repository

import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap
import com.minjaee.restareaapp.data.util.Resource

interface SearchRepository {
    suspend fun getSearchArea(y: Double, x: Double, radius: Int, query: String): Resource<SearchMap>
    suspend fun getNoLocationSearchArea(query: String): Resource<SearchMap>
    suspend fun saveSearchArea()
    suspend fun deleteSearchArea(document: SearchMap)
}