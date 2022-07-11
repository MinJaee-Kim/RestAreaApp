package com.minjaee.restareaapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.minjaee.restareaapp.domain.usecase.direction.GetDirectionUseCase
import com.minjaee.restareaapp.domain.usecase.restarea.GetRestAreaFoodUseCase
import com.minjaee.restareaapp.domain.usecase.restarea.GetRestAreaRoomUseCase

class RestAreaViewModelFactory(
    private val app: Application,
    private val getRestAreaRoomUseCase: GetRestAreaRoomUseCase,
    private val getRestAreaFoodUseCase: GetRestAreaFoodUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RestAreaViewModel(
            app,
            getRestAreaRoomUseCase,
            getRestAreaFoodUseCase
        ) as T
    }
}