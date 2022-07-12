package com.minjaee.restareaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap
import com.minjaee.restareaapp.databinding.ActivitySearchBinding
import com.minjaee.restareaapp.presentation.adapter.SearchAdapter
import com.minjaee.restareaapp.presentation.viewmodel.RestAreaViewModel
import com.minjaee.restareaapp.presentation.viewmodel.SearchViewModel
import com.minjaee.restareaapp.presentation.viewmodel.SearchViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: SearchViewModelFactory
    private lateinit var viewModel: SearchViewModel
    private lateinit var binding:ActivitySearchBinding
    private lateinit var adapter: SearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)
            .get(SearchViewModel::class.java)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SearchAdapter()
        binding.searchRecyclerView.adapter = adapter
        bindingSearchList()
    }

    private fun bindingSearchList(){
        binding.searchEt.doOnTextChanged { text, start, before, count ->
            if (text!="") {
                viewModel.getNoLocationSearch(text.toString())
            }
        }
        viewModel.noLocationSearch.observe(this) { response ->
            response.data.let {
                if (response!=null) {
                    adapter.setList(listOf(response.data) as List<SearchMap>)
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this, "데이터 에러", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}