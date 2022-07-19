package com.minjaee.restareaapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.minjaee.restareaapp.databinding.FragmentHomeBinding
import com.minjaee.restareaapp.presentation.viewmodel.DirectionViewModel
import com.minjaee.restareaapp.presentation.viewmodel.RestAreaViewModel
import com.minjaee.restareaapp.presentation.viewmodel.SearchViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.PathOverlay
import kotlinx.coroutines.*


class HomeFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mapView: MapView
    private lateinit var directionViewModel: DirectionViewModel
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private lateinit var navController: NavController
    private val markerList = ArrayList<Marker>()
    private val path = PathOverlay()
    private val coords = mutableListOf<LatLng>()
    lateinit var marker:Marker

    companion object {
        lateinit var restAreaViewModel: RestAreaViewModel
        lateinit var searchViewModel: SearchViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false) as ViewGroup

        mapView = rootView.findViewById<View>(R.id.naverMap) as MapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentHomeBinding = FragmentHomeBinding.bind(view)
        directionViewModel = (activity as MainActivity).directionViewModel
        searchViewModel = (activity as MainActivity).searchViewModel
        restAreaViewModel = (activity as MainActivity).restAreaViewModel

        fragmentHomeBinding.button.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_exploreFragment)
        }

        fragmentHomeBinding.button3.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        navController = findNavController()
        //TODO 가져오기
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("Location")
            ?.observe(viewLifecycleOwner) { result ->
                CoroutineScope(Dispatchers.IO).launch {
                    Log.i("TAG", "viewModelObserver: ")
                        directionViewModel.getDirections(
                            result.substring(0, result.indexOf("+")),
                            result.substring(result.indexOf("+")+1)
                        )
                }
            }
        navController.currentBackStackEntry?.savedStateHandle?.remove<String>("Location")
    }

    override fun onMapReady(naverMap: NaverMap) {
        viewModelObserver(naverMap)
    }

    private fun viewModelObserver(naverMap: NaverMap) {
        //TODO 마커지우기
        directionViewModel.markers.observe(viewLifecycleOwner) { directions ->
            markerList.forEach {
                it.map = null
            }
            markerList.clear()

            directions.forEach {
                marker = Marker(LatLng(it.latitude, it.longitude))
                marker.map = naverMap
                markerList.add(marker)
            }
        }

        directionViewModel.directions.observe(viewLifecycleOwner) { resources ->
            resources.data?.let {
                if (searchViewModel.isListEmpty){
                    coords.clear()
                    restAreaViewModel.routeRoomsList.clear()
                    restAreaViewModel.routeFoodsList.clear()
                    searchViewModel.searchList.clear()
                    MainActivity.nameHashSet.clear()
                    MainActivity.directionHashSet.clear()
                    path.map = null
                }
                val job = CoroutineScope(Dispatchers.IO).launch {
                    //TODO 로딩바 달기,
                    if (searchViewModel.isListEmpty) {
                        for (i in resources.data.route.traoptimal.get(0).path.indices) {
                            coords.add(
                                LatLng(
                                    resources.data.route.traoptimal[0].path[i].get(1),
                                    resources.data.route.traoptimal[0].path[i].get(0)
                                )
                            )
                            if (i % 30 == 0) {
                                searchViewModel.getSearch(
                                    resources.data.route.traoptimal[0].path[i].get(1),
                                    resources.data.route.traoptimal[0].path[i].get(0),
                                    2500,
                                    "휴게소",
                                )
                            }
                        }
                    }
                }

                val job2 = CoroutineScope(Dispatchers.IO).launch {
                    if (restAreaViewModel.isListEmpty) {
                        searchViewModel.locationHashSet.value?.forEach {
                            restAreaViewModel.getRooms(it)
                            restAreaViewModel.getFoods(it)
                        }
                    }
                }

                CoroutineScope(Dispatchers.Main).launch {
                    job.join()
                    job2.join()
                    searchViewModel.isListEmpty = false
                    restAreaViewModel.isListEmpty = false
                    searchViewModel.updateProvideListener()
                    if (coords.size>2) {
                        //TODO 이미지 지정
                        path.coords = coords
                        path.color = Color.RED
                        path.map = naverMap
                    }
                }
            }
            }
        }
}
