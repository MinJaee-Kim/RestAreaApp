package com.minjaee.restareaapp.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.model.keywordsearch.Document
import com.minjaee.restareaapp.domain.usecase.direction.GetDirectionUseCase
import com.minjaee.restareaapp.domain.usecase.search.GetSearchAreaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class RestAreaViewModel(
    private val app:Application,
    private val getDirectionUseCase: GetDirectionUseCase,
    private val getSearchAreaUseCase: GetSearchAreaUseCase
) : AndroidViewModel(app) {
    val directions : MutableLiveData<Response<GetDirections>> = MutableLiveData()
    val searchs: MutableLiveData<List<Document>> = MutableLiveData()

    fun getDirections(start: String, goal: String) = liveData {
        val directions = getDirectionUseCase.execute(start, goal)
        emit(directions)
    }

    fun getSearch(y: Double, x: Double, radius: Int, query: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val apiResult2 = getSearchAreaUseCase.execute(y, x, radius, query)
            searchs.postValue(apiResult2!!)
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
        }
    }
}