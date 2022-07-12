package com.minjaee.restareaapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.minjaee.restareaapp.domain.usecase.search.GetNoLocationSearchAreaUseCase
import com.minjaee.restareaapp.domain.usecase.search.GetSearchAreaUseCase

class SearchViewModelFactory(
    private val app: Application,
    private val getSearchAreaUseCase: GetSearchAreaUseCase,
    private val getNoLocationSearchAreaUseCase: GetNoLocationSearchAreaUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel (
            app,
            getSearchAreaUseCase,
            getNoLocationSearchAreaUseCase
        ) as T
    }
}