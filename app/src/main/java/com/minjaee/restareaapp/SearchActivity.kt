package com.minjaee.restareaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
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
    lateinit var viewModel: SearchViewModel
    private lateinit var binding:ActivitySearchBinding
    private lateinit var adapter: SearchAdapter
    private lateinit var start: String
    private lateinit var goal: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)
            .get(SearchViewModel::class.java)

        initBinding()
        initRecyclerView()
    }

    private fun initBinding() {
        val goalIntent = Intent(this, GoalSearchActivity::class.java)
        val startIntent = Intent(this, GoalSearchActivity::class.java)
        binding.searchStartEt.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_UP -> {
                        startActivity(startIntent)
                    }
                }
                return true
            }
        })

        binding.searchGoalEt.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_UP -> {
                        startActivity(goalIntent)
                    }
                }
                return true
            }
        })
    }

    private fun initRecyclerView() {
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SearchAdapter()
        binding.searchRecyclerView.adapter = adapter
        adapter.setOnItemClickListener {  }
        bindingSearchList()
    }

    private fun bindingSearchList(){
        viewModel.goal.observe(this, Observer {
            binding.searchGoalEt.setText(it.toString())
            Log.i("TAG", it.toString())
        })

        viewModel.updateGoal("asdf")
        //TODO
//        if (viewModel.start != null) {
//            binding.searchStartEt.setText(viewModel.start)
//        }
    }
}