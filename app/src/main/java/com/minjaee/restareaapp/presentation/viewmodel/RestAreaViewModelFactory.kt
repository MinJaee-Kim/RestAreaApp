package com.minjaee.restareaapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.minjaee.restareaapp.domain.usecase.direction.GetDirectionUseCase
import com.minjaee.restareaapp.domain.usecase.search.GetSearchAreaUseCase

class RestAreaViewModelFactory(
    private val app: Application,
    private val getDirectionUseCase: GetDirectionUseCase,
    private val getSearchAreaUseCase: GetSearchAreaUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RestAreaViewModel(
            app,
            getDirectionUseCase,
            getSearchAreaUseCase
        ) as T
    }
}