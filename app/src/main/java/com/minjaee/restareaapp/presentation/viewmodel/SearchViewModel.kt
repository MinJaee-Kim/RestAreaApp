package com.minjaee.restareaapp.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.domain.usecase.search.GetNoLocationSearchAreaUseCase
import com.minjaee.restareaapp.domain.usecase.search.GetSearchAreaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val app: Application,
    private val getSearchAreaUseCase: GetSearchAreaUseCase,
    private val getNoLocationSearchAreaUseCase: GetNoLocationSearchAreaUseCase
) : AndroidViewModel(app) {
    val search: MutableLiveData<Resource<SearchMap>> = MutableLiveData()
    val noLocationSearch: MutableLiveData<Resource<SearchMap>> = MutableLiveData()

    private var start = MutableLiveData<String>()
    val startData : LiveData<String>
    get() = start

    val goal = MutableLiveData<String>()

    var startLocation: String? = null
    var goalLocation: String? = null

    fun getSearch(y: Double, x: Double, radius: Int, query: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val apiResult = getSearchAreaUseCase.execute(y, x, radius, query)
            search.postValue(apiResult!!)
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
        }
    }

    fun getNoLocationSearch(query: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val apiResult = getNoLocationSearchAreaUseCase.execute(query)
            noLocationSearch.postValue(apiResult!!)
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
        }
    }

    fun updateGoal(goal: String){
        this.goal.postValue(goal)
    }
}