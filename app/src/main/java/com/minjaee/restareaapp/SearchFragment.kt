package com.minjaee.restareaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.minjaee.restareaapp.databinding.FragmentSearchBinding
import com.minjaee.restareaapp.presentation.adapter.SearchAdapter
import com.minjaee.restareaapp.presentation.viewmodel.RestAreaViewModel
import com.minjaee.restareaapp.presentation.viewmodel.SearchViewModel
import com.minjaee.restareaapp.presentation.viewmodel.SearchViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    @Inject
    lateinit var factory: SearchViewModelFactory
    companion object{
        lateinit var viewModel: SearchViewModel
    }

    private lateinit var homeSearchViewModel: SearchViewModel
    private lateinit var homeRestAreaViewModel: RestAreaViewModel

    private lateinit var searchBinding: FragmentSearchBinding
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_search, container, false) as ViewGroup

        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBinding = FragmentSearchBinding.bind(view)
        viewModel = ViewModelProvider(this, factory)
            .get(SearchViewModel::class.java)
        homeSearchViewModel = (HomeFragment).searchViewModel
        homeRestAreaViewModel = (HomeFragment).restAreaViewModel


        initBinding()
        initRecyclerView()
    }

    private fun initBinding() {
        searchBinding.searchStartEt.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_UP -> {
                        if (view != null) {
                            view.findNavController().navigate(R.id.action_searchFragment_to_startSearchFragment)
                        }
                    }
                }
                return true
            }
        })

        searchBinding.searchGoalEt.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_UP -> {
                        if (view != null) {
                            view.findNavController().navigate(R.id.action_searchFragment_to_goalSearchFragment)
                        }
                    }
                }
                return true
            }
        })

        searchBinding.button2.setOnClickListener {
            //TODO 출발지 및 도착지 둘다 초기화
            if (searchBinding.searchStartEt.text.isEmpty()||searchBinding.searchGoalEt.text.isEmpty()) {
                Toast.makeText(context, "출발지와 목적지를 입력해주세요.", Toast.LENGTH_LONG).show()
            } else {
                homeSearchViewModel.isListEmpty = true
                homeRestAreaViewModel.isListEmpty = true

                val navController = findNavController()

                navController.previousBackStackEntry?.savedStateHandle?.set(
                    "Location", viewModel.startLocation
                            + "+" + viewModel.goalLocation
                )
                navController.popBackStack()
            }
        }
    }

    private fun initRecyclerView() {
        //TODO 로그 달기
//        binding.searchRecyclerView.layoutManager = LinearLayoutManager(this)
//        adapter = SearchAdapter()
//        binding.searchRecyclerView.adapter = adapter
//        adapter.setOnItemClickListener {  }
        bindingSearchList()
    }

    private fun bindingSearchList(){
        viewModel.goal.observe(viewLifecycleOwner, Observer {
            searchBinding.searchGoalEt.setText(it.toString())
        })

        viewModel.start.observe(viewLifecycleOwner, Observer {
            searchBinding.searchStartEt.setText(it.toString())
        })
    }
}