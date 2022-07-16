package com.minjaee.restareaapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.minjaee.restareaapp.databinding.FragmentExploreBinding
import com.minjaee.restareaapp.presentation.adapter.RoomsAdapter
import com.minjaee.restareaapp.presentation.viewmodel.RestAreaViewModel

class ExploreFragment : Fragment() {
    //TODO 리사이클러뷰 만들기
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
        roomsAdapter = RoomsAdapter()
        roomsAdapter.setList(restAreaViewModel.routeRoomsList)
        binding.areaRv.apply {
            adapter = roomsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        roomsAdapter.setOnItemClickListener {
            //TODO 아코디언 만들기
            Log.i("TAG", "확인")
        }
        bindingViews()
    }

    private fun bindingViews() {

    }
}