package com.minjaee.restareaapp.domain.usecase.search

import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.domain.repository.SearchRepository

class GetSearchAreaUseCase(private val searchRepository: SearchRepository) {
    suspend fun execute(y: Double, x: Double, radius: Int, query: String): Resource<Document>
    = searchRepository.getSearchArea(y, x, radius, query)
}