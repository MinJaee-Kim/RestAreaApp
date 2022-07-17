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
import androidx.recyclerview.widget.LinearLayoutManager
import com.minjaee.restareaapp.databinding.FragmentStartBinding
import com.minjaee.restareaapp.presentation.adapter.SearchAdapter
import com.minjaee.restareaapp.presentation.viewmodel.SearchViewModel
import com.minjaee.restareaapp.presentation.viewmodel.SearchViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StartSearchFragment : Fragment() {
    @Inject
    lateinit var factory: SearchViewModelFactory
    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentStartBinding
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var imm: InputMethodManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartBinding.bind(view)
        viewModel = (SearchFragment).viewModel
        initRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_start, container, false) as ViewGroup

        return rootView
    }

    private fun initRecyclerView() {
        searchAdapter = SearchAdapter()
        binding.startRecyclerView.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        searchAdapter.setOnItemClickListener {
            viewModel.updateStart(it.placeName)
            viewModel.updateStartLocation(it.x+","+it.y)
            viewModel.noLocationSearch.value = null
            view?.findNavController()?.popBackStack()
        }
        bindingSearchList()
    }

    private fun bindingSearchList(){
        binding.startSearchEt.doOnTextChanged { text, start, before, count ->
            if (text!="") {
                viewModel.getNoLocationSearch(text.toString())
            }
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

        binding.startSearchEt.requestFocus()
        imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.startSearchEt, 0)
    }
}