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
import com.minjaee.restareaapp.presentation.viewmodel.RestAreaViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.PathOverlay




class HomeFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mapView: MapView
    private lateinit var viewModel: DirectionViewModel
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
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
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onMapReady(naverMap: NaverMap) {
        val path = PathOverlay()
        val coords = mutableListOf<LatLng>()
        viewModel.directions.observe(viewLifecycleOwner) { resources ->
            resources.data?.let {
                for (i in resources.data.route.traoptimal.get(0).path.indices) {
                    coords.add(
                        LatLng(
                            resources.data.route.traoptimal[0].path[i].get(1),
                            resources.data.route.traoptimal[0].path[i].get(0)
                        )
                    )
                }

                if (coords.size>2) {
                    path.coords = coords
                    path.color = Color.RED
                    path.map = naverMap
                }
            }
        }
    }
}