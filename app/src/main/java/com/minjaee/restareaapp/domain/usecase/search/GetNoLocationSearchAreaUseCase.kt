package com.minjaee.restareaapp.domain.usecase.search

import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.domain.repository.SearchRepository

class GetNoLocationSearchAreaUseCase(private val searchRepository: SearchRepository) {
    suspend fun execute(query: String): Resource<SearchMap>
    = searchRepository.getNoLocationSearchArea(query)
}