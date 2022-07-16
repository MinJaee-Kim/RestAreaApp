package com.minjaee.restareaapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    companion object {
        lateinit var restAreaViewModel: RestAreaViewModel
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

        val navController = findNavController()
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("Location")
            ?.observe(viewLifecycleOwner) { result ->
                directionViewModel.getDirections(
                    result.substring(0, result.indexOf("+")),
                    result.substring(result.indexOf("+")+1)
                )
            }
    }

    override fun onMapReady(naverMap: NaverMap) {
        viewModelObserver(naverMap)
    }

    private fun viewModelObserver(naverMap: NaverMap) {
        val path = PathOverlay()
        val coords = mutableListOf<LatLng>()
        directionViewModel.markers.observe(viewLifecycleOwner) { directions ->
            val markerList = ArrayList<Marker>()
            var index = 0
            for (i in directions.indices) {
                markerList.add(Marker())
            }

            directions.forEach {
                markerList.get(index).position = LatLng(it.latitude, it.longitude)
                markerList.get(index).map = naverMap
                index++
            }
        }

        directionViewModel.directions.observe(viewLifecycleOwner) { resources ->
            resources.data?.let {
                runBlocking {
                    //TODO 로딩바 달기
                    launch {
                        for (i in resources.data.route.traoptimal.get(0).path.indices) {
                            coords.add(
                                LatLng(
                                    resources.data.route.traoptimal[0].path[i].get(1),
                                    resources.data.route.traoptimal[0].path[i].get(0)
                                )
                            )
                            if (i%40==0) {
                                searchViewModel.getSearch(
                                    resources.data.route.traoptimal[0].path[i].get(1),
                                    resources.data.route.traoptimal[0].path[i].get(0),
                                    3000,
                                    "휴게소",
                                )
                            }
                        }
                    }.join()

                    //TODO restArea 정보 가져오기
                }
                searchViewModel.updateProvideListener()


                runBlocking {
                    launch {
                        searchViewModel.locationHashSet.value?.forEach {
                            restAreaViewModel.getRooms(it)
                            restAreaViewModel.getFoods(it)
                        }
                    }.join()
                }

                for (i in restAreaViewModel.routeRoomsList.indices) {
                    Log.i("TAG", restAreaViewModel.routeFoodsList.get(i).data.toString())
                    Log.i("TAG", restAreaViewModel.routeRoomsList.get(i).data?.list.toString())
                }
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
