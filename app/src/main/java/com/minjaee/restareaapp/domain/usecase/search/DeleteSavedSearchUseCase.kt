package com.minjaee.restareaapp.domain.usecase.search

import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap
import com.minjaee.restareaapp.domain.repository.SearchRepository

class DeleteSavedSearchUseCase(private val searchRepository: SearchRepository) {
    suspend fun execute(searchMap: SearchMap)=searchRepository.deleteSearchArea(searchMap)
}