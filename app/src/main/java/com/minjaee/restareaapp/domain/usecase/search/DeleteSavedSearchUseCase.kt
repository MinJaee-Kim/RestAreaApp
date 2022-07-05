package com.minjaee.restareaapp.domain.usecase.search

import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.domain.repository.SearchRepository

class DeleteSavedSearchUseCase(private val searchRepository: SearchRepository) {
    suspend fun execute(document: Document)=searchRepository.deleteSearchArea(document)
}