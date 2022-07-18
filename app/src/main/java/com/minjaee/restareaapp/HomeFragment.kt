package com.minjaee.restareaapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    var marker = Marker()

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
                runBlocking {
                    launch {
                        directionViewModel.getDirections(
                            result.substring(0, result.indexOf("+")),
                            result.substring(result.indexOf("+")+1)
                        )
                    }.join()
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
                marker.position = LatLng(it.latitude, it.longitude)
                marker.map = naverMap
                markerList.add(marker)
            }
        }

        directionViewModel.directions.observe(viewLifecycleOwner) { resources ->
            resources.data?.let {
                if (searchViewModel.isListEmpty){
                    coords.clear()
                    searchViewModel.searchList.clear()
                    path.map = null
                }
                runBlocking {
                    //TODO 로딩바 달기,
                    launch {
                        if (searchViewModel.isListEmpty) {
                            for (i in resources.data.route.traoptimal.get(0).path.indices) {
                                coords.add(
                                    LatLng(
                                        resources.data.route.traoptimal[0].path[i].get(1),
                                        resources.data.route.traoptimal[0].path[i].get(0)
                                    )
                                )
                                if (i % 40 == 0) {
                                    searchViewModel.getSearch(
                                        resources.data.route.traoptimal[0].path[i].get(1),
                                        resources.data.route.traoptimal[0].path[i].get(0),
                                        3000,
                                        "휴게소",
                                    )
                                }
                            }
                        }
                    }.join()
                }

                searchViewModel.isListEmpty = false
                searchViewModel.updateProvideListener()


                runBlocking {
                    //TODO locationHashset과 foodHashset 따로 존재
                    launch {
                        if (restAreaViewModel.isListEmpty) {
                            searchViewModel.locationHashSet.value?.forEach {
                                restAreaViewModel.getRooms(it)
                                restAreaViewModel.getFoods(it)
                            }
                        }
                    }.join()
                }

                restAreaViewModel.isListEmpty = false
            }


                if (coords.size>2) {
                    //TODO 이미지 지정
                    path.coords = coords
                    path.color = Color.RED
                    path.map = naverMap
                }
            }
        }
}
