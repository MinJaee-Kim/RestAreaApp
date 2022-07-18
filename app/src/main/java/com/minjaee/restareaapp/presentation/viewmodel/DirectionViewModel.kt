package com.minjaee.restareaapp.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.minjaee.restareaapp.data.model.getdirection.GetDirections
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.domain.usecase.direction.GetDirectionUseCase
import com.naver.maps.geometry.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DirectionViewModel(
    private val app: Application,
    private val getDirectionUseCase: GetDirectionUseCase
) : AndroidViewModel(app) {
    val directions : MutableLiveData<Resource<GetDirections>> = MutableLiveData()
    val markers: MutableLiveData<HashSet<LatLng>> = MutableLiveData()

    suspend fun getDirections(start: String, goal: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val apiResult = getDirectionUseCase.execute(start, goal)
            directions.postValue(apiResult!!)
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
        }
    }.join()

    fun updateMarkers(hashSet: HashSet<LatLng>){
        markers.postValue(hashSet)
    }
}