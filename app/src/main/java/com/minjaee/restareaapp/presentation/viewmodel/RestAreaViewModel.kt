package com.minjaee.restareaapp.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.domain.usecase.direction.GetDirectionUseCase
import com.minjaee.restareaapp.domain.usecase.restarea.GetRestAreaFoodUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class RestAreaViewModel(
    private val app:Application,
    private val getDirectionUseCase: GetDirectionUseCase,
    private val getRestAreaFoodUseCase: GetRestAreaFoodUseCase
) : AndroidViewModel(app) {
    val directions : MutableLiveData<Resource<GetDirections>> = MutableLiveData()
    val searchs: MutableLiveData<List<Document>> = MutableLiveData()
    val foods: MutableLiveData<Resource<RestAreaFood>> = MutableLiveData()

    fun getDirections(start: String, goal: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val apiResult2 = getDirectionUseCase.execute(start, goal)
            directions.postValue(apiResult2!!)
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
        }
    }

    fun getFoods(stdRestNm: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val apiResult3 = getRestAreaFoodUseCase.execute(stdRestNm)
            foods.postValue(apiResult3!!)
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
        }
    }
}