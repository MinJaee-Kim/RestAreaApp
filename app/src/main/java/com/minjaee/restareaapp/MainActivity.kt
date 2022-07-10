package com.minjaee.restareaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.minjaee.restareaapp.databinding.ActivityMainBinding
import com.minjaee.restareaapp.presentation.viewmodel.RestAreaViewModel
import com.minjaee.restareaapp.presentation.viewmodel.RestAreaViewModelFactory
import com.minjaee.restareaapp.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: RestAreaViewModelFactory
    lateinit var viewModel: RestAreaViewModel
//    lateinit var viewModel2: SearchViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, factory)
            .get(RestAreaViewModel::class.java)
//        viewModel2 = ViewModelProvider(this)
//            .get(SearchViewModel::class.java)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.homeBtn.setOnClickListener {
            viewModel.getDirections("128.416926, 34.885074", "126.9776692, 37.5591786")
//            viewModel2.getSearch(37.5591786, 126.9776692, 20000, "휴게소")
            viewModel.getFoods("서울")
        }
    }
}