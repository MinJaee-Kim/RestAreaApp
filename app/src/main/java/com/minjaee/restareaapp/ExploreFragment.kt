package com.minjaee.restareaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.minjaee.restareaapp.databinding.FragmentExploreBinding
import com.minjaee.restareaapp.presentation.adapter.RoomsAdapter
import com.minjaee.restareaapp.presentation.viewmodel.RestAreaViewModel

class ExploreFragment : Fragment() {
    private lateinit var binding: FragmentExploreBinding
    private lateinit var roomsAdapter: RoomsAdapter
    private lateinit var restAreaViewModel: RestAreaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_explore, container, false) as ViewGroup

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExploreBinding.bind(view)
        restAreaViewModel = (HomeFragment).restAreaViewModel
        initRecyclerView()
    }

    private fun initRecyclerView() {
        roomsAdapter = RoomsAdapter(context!!)
        roomsAdapter.setList(restAreaViewModel.routeRoomsList, restAreaViewModel.routeFoodsList)
        binding.areaRv.apply {
            adapter = roomsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        bindingViews()
    }

    private fun bindingViews() {

    }
}