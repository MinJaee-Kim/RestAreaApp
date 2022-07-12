package com.minjaee.restareaapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.minjaee.restareaapp.databinding.FragmentHomeBinding
import com.minjaee.restareaapp.presentation.viewmodel.RestAreaViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.PathOverlay




class HomeFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mapView: MapView
    private lateinit var viewModel: RestAreaViewModel
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


        viewModel.foods.observe(viewLifecycleOwner) { resources ->
            resources.data?.let { Log.i("asdv", it.count.toString()) }
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        val path = PathOverlay()
        val coords = mutableListOf(
            LatLng(
                34.8851879, 128.4171474),
            LatLng(
                34.8849340, 128.4173405),
            LatLng(
                34.8845520, 128.4176547),
            LatLng(
                34.8841747, 128.4179405),
            LatLng(
                34.8839162, 128.4181368),
            LatLng(
                34.8837521, 128.4182608),
            LatLng(
                34.8838161, 128.4183742),
            LatLng(
                34.8838882, 128.4185074),
            LatLng(
                34.8841745, 128.4190369),
            LatLng(
                34.8843452, 128.4193649),
            LatLng(
                34.8843808, 128.4194299),
            LatLng(
                34.8844092, 128.4194806),
            LatLng(
                34.8847995, 128.4202246),
            LatLng(
                34.8849657, 128.4205416),
            LatLng(
                34.8850412, 128.4206869),
            LatLng(
                34.8851372, 128.4208729),
            LatLng(
                34.8854082, 128.4213990),
            LatLng(
                34.8856091, 128.4217821),
            LatLng(
                34.8856909, 128.4219373),
            LatLng(
                34.8858596, 128.4218112),
            LatLng(
                34.8873151, 128.4207243),
            LatLng(
                34.8874493, 128.4206241),
            LatLng(
                34.8876171, 128.4204902),
            LatLng(
                34.8880082, 128.4201740),
            LatLng(
                34.8886122, 128.4197167),
            LatLng(
                34.8887827, 128.4195873),
            LatLng(
                34.8888413, 128.4194621),
            LatLng(
                34.8888701, 128.4192348),
            LatLng(
                34.8888749, 128.4191998),
            LatLng(
                34.8888992, 128.4190874),
            LatLng(
                34.8889307, 128.4189662),
            LatLng(
                34.888941, 128.41892489)
        )

//        directions.observe(viewLifecycleOwner) { resource ->
//            resource.data?.let {
//                Log.i("TAG", it.route.traoptimal.toString())
//            }
//        }

        path.coords = coords
        path.color = Color.RED
        path.map = naverMap
    }
}