package com.minjaee.restareaapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.domain.usecase.direction.GetDirectionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(val getDirectionUseCase: GetDirectionUseCase) : ViewModel() {
    val directions : MutableLiveData<List<GetDirections>> = MutableLiveData()

    fun getDirections(start: String, goal: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val apiResult = getDirectionUseCase.execute(start, goal)
            directions.postValue(apiResult)
        } catch (exception: Exception) {
            Log.i("TAG", exception.message.toString())
        }
    }
}