package com.minjaee.restareaapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.minjaee.restareaapp.domain.usecase.direction.GetDirectionUseCase

class DirectionViewModelFactory(
    private val app: Application,
    private val getDirectionUseCase: GetDirectionUseCase
) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DirectionViewModel(
            app,
            getDirectionUseCase
        ) as T
    }
}