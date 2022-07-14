package com.minjaee.restareaapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.minjaee.restareaapp.databinding.FragmentHomeBinding
import com.minjaee.restareaapp.presentation.viewmodel.DirectionViewModel
import com.minjaee.restareaapp.presentation.viewmodel.SearchViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.PathOverlay
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flow
import kotlin.math.log


class HomeFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mapView: MapView
    private lateinit var directionViewModel: DirectionViewModel
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    private var completeListener = false

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
    }

    override fun onMapReady(naverMap: NaverMap) {
        viewModelObserver(naverMap)
    }

    private fun viewModelObserver(naverMap: NaverMap) {
        val path = PathOverlay()
        val marker = Marker()
        val coords = mutableListOf<LatLng>()
        directionViewModel.markers.observe(viewLifecycleOwner) { directions ->

            directions.forEach {
                marker.position = LatLng(it.longitude, it.latitude)
            }

            if (directions.isNotEmpty()) {
                marker.map = naverMap
            }
        }

        directionViewModel.directions.observe(viewLifecycleOwner) { resources ->
            resources.data?.let {
                runBlocking {
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
                                    10000,
                                    "휴게소",
                                )
                            }
                        }
                        Log.i("TAG", "asdf")
                    }.join()
                }


                //TODO
                Log.i("TAG", searchViewModel.searchList.get(0).data.toString())
            }


                if (coords.size>2) {
                    path.coords = coords
                    path.color = Color.RED
                    path.map = naverMap
                }
            }
        }
}
