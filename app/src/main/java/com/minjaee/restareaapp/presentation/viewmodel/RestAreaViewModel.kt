package com.minjaee.restareaapp.presentation .viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap
import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.domain.usecase.restarea.GetRestAreaFoodUseCase
import com.minjaee.restareaapp.domain.usecase.restarea.GetRestAreaRoomUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class RestAreaViewModel(
    private val app:Application,
    private val getRestAreaRoomUseCase: GetRestAreaRoomUseCase,
    private val getRestAreaFoodUseCase: GetRestAreaFoodUseCase
) : AndroidViewModel(app) {
    val routeRoomsList: ArrayList<Resource<RestAreaRoom>> = ArrayList()
    val routeFoodsList: ArrayList<Resource<RestAreaFood>> = ArrayList()

    var isListEmpty = true

    suspend fun getFoods(stdRestNm: String) = CoroutineScope(Dispatchers.Main).launch {
        try {
            val apiResult = getRestAreaFoodUseCase.execute(stdRestNm)
            if (apiResult != null) {
                routeFoodsList.add(apiResult)
            }
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
        }
    }.join()

    suspend fun getRooms(serviceAreaName: String) = CoroutineScope(Dispatchers.Main).launch {
        try {
            val apiResult = getRestAreaRoomUseCase.execute(serviceAreaName)
            if (apiResult.data?.list?.get(0)?.serviceAreaName!=null) {
                routeRoomsList.add(apiResult)
            }
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
        }
    }.join()
}