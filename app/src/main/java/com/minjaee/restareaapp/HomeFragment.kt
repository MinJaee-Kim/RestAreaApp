package com.minjaee.restareaapp

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.minjaee.restareaapp.databinding.FragmentHomeBinding
import com.minjaee.restareaapp.presentation.others.LottieDialogFragment
import com.minjaee.restareaapp.presentation.viewmodel.DirectionViewModel
import com.minjaee.restareaapp.presentation.viewmodel.RestAreaViewModel
import com.minjaee.restareaapp.presentation.viewmodel.SearchViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
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
    private lateinit var naverMap: NaverMap
    private val lottieDialogFragment by lazy { LottieDialogFragment() }

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

        fragmentHomeBinding.homeExploreBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_exploreFragment)
        }

        fragmentHomeBinding.homeSearchBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        initBinding()
    }

    private fun initBinding() {
        navController = findNavController()
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("Location")
            ?.observe(viewLifecycleOwner) { result ->
                CoroutineScope(Dispatchers.Main).launch {
                    directionViewModel.getDirections(
                        result.substring(0, result.indexOf("+")),
                        result.substring(result.indexOf("+")+1)
                    )
                }
                naverMap.moveCamera(CameraUpdate.scrollTo(
                    LatLng(
                        result.substring(result.indexOf(",")+1, result.indexOf("+")).toDouble()
                    , result.substring(0, result.indexOf(",")).toDouble()
                )
                ))
            }
        navController.currentBackStackEntry?.savedStateHandle?.remove<String>("Location")

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

        if (searchViewModel.isListEmpty){
            coords.clear()
            restAreaViewModel.routeRoomsList.clear()
            restAreaViewModel.routeFoodsList.clear()
            searchViewModel.searchList.clear()
            MainActivity.nameHashSet.clear()
            MainActivity.directionHashSet.clear()
            directionViewModel.directions.value = null
            viewModelStore.clear()
            path.map = null
        }

        directionViewModel.directions.observe(viewLifecycleOwner) { resources ->
            resources?.data?.let {
                CoroutineScope(Dispatchers.Main).launch {
                    launch {
                        if (searchViewModel.isListEmpty) {
                            if (!lottieDialogFragment.isAdded){
                                lottieDialogFragment.show(childFragmentManager, "loader")
                            } else {
                                lottieDialogFragment.onStart()
                            }

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
                    }.join()

                    searchViewModel.updateProvideListener()

                    launch {
                        if (restAreaViewModel.isListEmpty) {
                            searchViewModel.locationHashSet.value?.forEach {
                                restAreaViewModel.getRooms(it)
                                restAreaViewModel.getFoods(it)
                            }
                        }
                    }.join()

                    searchViewModel.isListEmpty = false
                    restAreaViewModel.isListEmpty = false
                    if (coords.size>2) {
                        //TODO 이미지 지정
                        path.coords = coords
                        path.color = Color.RED
                        path.map = naverMap
                    }
                    if (restAreaViewModel.routeRoomsList.isNotEmpty()) {
                        fragmentHomeBinding.homeExploreBtn.visibility = View.VISIBLE
                    } else {
                        fragmentHomeBinding.homeExploreBtn.visibility = View.INVISIBLE
                        Toast.makeText(context, "경로상 휴게소가 없습니다.", Toast.LENGTH_LONG).show()
                    }
                    lottieDialogFragment.dismiss()
                }
            }
        }
    }
}
