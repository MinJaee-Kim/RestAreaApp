package com.minjaee.restareaapp.domain.usecase.search

import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.domain.repository.SearchRepository

class GetNoLocationSearchAreaUseCase(private val searchRepository: SearchRepository) {
    suspend fun execute(query: String): Resource<Document>
    = searchRepository.getNoLocationSearchArea(query)
}