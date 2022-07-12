package com.minjaee.restareaapp.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.domain.usecase.restarea.GetRestAreaFoodUseCase
import com.minjaee.restareaapp.domain.usecase.restarea.GetRestAreaRoomUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class RestAreaViewModel(
    private val app:Application,
    private val getRestAreaRoomUseCase: GetRestAreaRoomUseCase,
    private val getRestAreaFoodUseCase: GetRestAreaFoodUseCase
) : AndroidViewModel(app) {
    val rooms : MutableLiveData<Resource<RestAreaRoom>> = MutableLiveData()
    val foods: MutableLiveData<Resource<RestAreaFood>> = MutableLiveData()
    fun getFoods(stdRestNm: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val apiResult = getRestAreaFoodUseCase.execute(stdRestNm)
            foods.postValue(apiResult!!)
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
        }
    }

    fun getRooms(serviceAreaName: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val apiResult = getRestAreaRoomUseCase.execute(serviceAreaName)
            rooms.postValue(apiResult!!)
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
        }
    }
}