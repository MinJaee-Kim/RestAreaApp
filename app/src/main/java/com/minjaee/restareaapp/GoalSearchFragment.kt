package com.minjaee.restareaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.minjaee.restareaapp.databinding.FragmentGoalBinding
import com.minjaee.restareaapp.presentation.adapter.SearchAdapter
import com.minjaee.restareaapp.presentation.viewmodel.SearchViewModel
import com.minjaee.restareaapp.presentation.viewmodel.SearchViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GoalSearchFragment : Fragment() {
    @Inject
    lateinit var factory: SearchViewModelFactory
    private lateinit var viewModel: SearchViewModel
    private lateinit var binding:FragmentGoalBinding
    private lateinit var searchAdapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGoalBinding.bind(view)
        viewModel = (SearchFragment).viewModel
        initRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_goal, container, false) as ViewGroup

        return rootView
    }

    private fun initRecyclerView() {
        searchAdapter = SearchAdapter()
        binding.goalRecyclerView.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        searchAdapter.setOnItemClickListener {
            viewModel.updateGoal(it.placeName)
            viewModel.updateGoalLocation(it.x+","+it.y)
            //TODO
            view?.findNavController()?.popBackStack()
        }
        bindingSearchList()
    }

    private fun bindingSearchList(){
        binding.goalSearchEt.doOnTextChanged { text, start, before, count ->
            if (text!="") {
                viewModel.getNoLocationSearch(text.toString())
            }
        }

        viewModel.noLocationSearch.observe(viewLifecycleOwner) { response ->
            response.data.let {
                if (response.data!=null) {
                    searchAdapter.setList(response.data)
                    searchAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(context, "데이터 에러", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}