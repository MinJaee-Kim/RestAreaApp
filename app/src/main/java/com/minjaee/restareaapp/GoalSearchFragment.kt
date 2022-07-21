package com.minjaee.restareaapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
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
    private lateinit var imm: InputMethodManager

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
        binding.goalRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        searchAdapter.setOnItemClickListener {
            viewModel.updateGoal(it.placeName)
            viewModel.updateGoalLocation(it.x+","+it.y)
            viewModel.noLocationSearch.value = null
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

        binding.goalSearchBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.noLocationSearch.observe(viewLifecycleOwner) { response ->
            if (response!=null) {
                response.data.let {
                    if (response.data != null) {
                        searchAdapter.setList(response.data)
                        searchAdapter.notifyDataSetChanged()
                    }
                }
            } else {
                searchAdapter.setList(null)
            }
        }

        binding.goalSearchEt.requestFocus()
        imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.goalSearchEt, 0)
    }
}