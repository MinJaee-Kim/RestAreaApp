package com.minjaee.restareaapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.minjaee.restareaapp.databinding.FragmentHomeBinding
import com.minjaee.restareaapp.presentation.viewmodel.RestAreaViewModel

class HomeFragment : Fragment() {
    private lateinit var viewModel: RestAreaViewModel
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentHomeBinding = FragmentHomeBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        val responseLiveData = viewModel.getDirections("128.416926, 34.885074", "126.9776692, 37.5591786")

        viewModel.directions.observe(viewLifecycleOwner) { resource ->
            Log.i("sdaf", resource.message)
        }

        viewModel.searchs.observe(viewLifecycleOwner) { resources ->
            Log.i("TAG", resources.toString())
        }

        viewModel.foods.observe(viewLifecycleOwner) { resources ->
            Log.i("TAG", resources.data!!.message)
        }
        //TODO
        //TODO
    }
}