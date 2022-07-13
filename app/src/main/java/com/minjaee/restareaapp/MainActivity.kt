package com.minjaee.restareaapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.minjaee.restareaapp.databinding.ActivityMainBinding
import com.minjaee.restareaapp.presentation.viewmodel.DirectionViewModel
import com.minjaee.restareaapp.presentation.viewmodel.DirectionViewModelFactory
import com.minjaee.restareaapp.presentation.viewmodel.RestAreaViewModel
import com.minjaee.restareaapp.presentation.viewmodel.RestAreaViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: DirectionViewModelFactory
    lateinit var viewModel: DirectionViewModel

    private lateinit var binding: ActivityMainBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var start: String
    private lateinit var goal: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, factory)
            .get(DirectionViewModel::class.java)
//        viewModel2 = ViewModelProvider(this)
//            .get(SearchViewModel::class.java)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        activityResult()
    }

    private fun activityResult() {
        activityResultLauncher =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    viewModel.getDirections(
                        result.data?.getStringExtra("startLocation").toString(),
                        result.data?.getStringExtra("goalLocation").toString()
                    )
                }
            }

        val intent = Intent(this, SearchActivity::class.java)

        binding.homeBtn.setOnClickListener {
            activityResultLauncher.launch(intent)
        }

    }
}