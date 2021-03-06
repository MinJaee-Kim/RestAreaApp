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
import kotlinx.coroutines.*

class SearchViewModel(
    private val app: Application,
    private val getSearchAreaUseCase: GetSearchAreaUseCase,
    private val getNoLocationSearchAreaUseCase: GetNoLocationSearchAreaUseCase
) : AndroidViewModel(app) {
    val searchList: ArrayList<Resource<SearchMap>> = ArrayList()
    val noLocationSearch: MutableLiveData<Resource<SearchMap>> = MutableLiveData()
    var locationHashSet: MutableLiveData<HashSet<String>> = MutableLiveData()

    var isListEmpty = true

    var start = MutableLiveData<String>()
    var goal = MutableLiveData<String>()

    var provideListener = MutableLiveData<Int>()

    lateinit var startLocation: String
    lateinit var goalLocation: String


    suspend fun getSearch(y: Double, x: Double, radius: Int, query: String) = CoroutineScope(Dispatchers.Main).launch {
        try {
            val apiResult = getSearchAreaUseCase.execute(y, x, radius, query)
            searchList.add(apiResult)
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
        }
    }.join()

    fun getNoLocationSearch(query: String) = CoroutineScope(Dispatchers.IO).launch {
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

    fun updateStart(start: String){
        this.start.postValue(start)
    }

    fun updateProvideListener() {
        provideListener.value = (provideListener.value)?.plus(1)
    }

    fun updateStartLocation(startLocation: String){
        this.startLocation = startLocation
    }

    fun updateGoalLocation(goalLocation: String){
        this.goalLocation = goalLocation
    }

    fun updateLocationHashSet(locationHash: HashSet<String>){
        this.locationHashSet.value = locationHash
    }
}