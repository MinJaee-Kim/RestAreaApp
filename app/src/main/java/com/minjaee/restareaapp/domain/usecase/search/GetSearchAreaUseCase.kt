package com.minjaee.restareaapp.domain.usecase.search

import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.domain.repository.SearchRepository
import retrofit2.Response

class GetSearchAreaUseCase(private val searchRepository: SearchRepository) {
    suspend fun execute(): Response<Document>? = searchRepository.getSearchArea()
}