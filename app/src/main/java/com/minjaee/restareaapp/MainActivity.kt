package com.minjaee.restareaapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.minjaee.restareaapp.databinding.ActivityMainBinding
import com.minjaee.restareaapp.presentation.viewmodel.*
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var directionFactory: DirectionViewModelFactory
    @Inject
    lateinit var searchFactory: SearchViewModelFactory
    lateinit var directionViewModel: DirectionViewModel
    lateinit var searchViewModel: SearchViewModel

    private lateinit var binding: ActivityMainBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    private val nameHashSet = HashSet<String>()
    private val directionHashSet = HashSet<LatLng>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        directionViewModel = ViewModelProvider(this, directionFactory)
            .get(DirectionViewModel::class.java)
        searchViewModel = ViewModelProvider(this, searchFactory)
            .get(SearchViewModel::class.java)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        activityResult()
        viewModelObserver()
    }

    private fun activityResult() {
        activityResultLauncher =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    directionViewModel.getDirections(
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

    private fun viewModelObserver() {
//        searchViewModel.search.observe(this, Observer {
//            for (i in it.data?.documents?.indices!!) {
//                if (it.data.documents.get(i).categoryName.endsWith("고속도로휴게소")){
//                    nameHashSet.add(it.data.documents.get(i).placeName.substring(
//                        0,
//                        it.data.documents.get(i).placeName.indexOf("휴게소")) + "(" +
//                            it.data.documents.get(i).placeName.substring(
//                                it.data.documents.get(i).placeName.indexOf(" ")+1,
//                                it.data.documents.get(i).placeName.indexOf("방향")
//                            )
//                            + ")"
//                    )
//
//                    directionHashSet.add(LatLng(
//                        it.data.documents.get(i).y.toDouble(),
//                        it.data.documents.get(i).x.toDouble())
//                    )
//                }
//            }
//            Log.i("TAG", "onCreate: "+ nameHashSet)
////            directionViewModel.updateMarkers(directionHashSet)
//        })

        searchViewModel.provideListener.observe(this, Observer {
            for (i in searchViewModel.searchList.indices){
                for (j in searchViewModel.searchList.get(i).data?.documents?.indices!!) {
                    if (searchViewModel.searchList.get(i).data!!.documents.get(j).categoryName.endsWith("고속도로휴게소")){
                        nameHashSet.add(searchViewModel.searchList.get(i).data!!.documents.get(j).placeName.substring(
                            0,
                            searchViewModel.searchList.get(i).data!!.documents.get(j).placeName.indexOf("휴게소")) + "(" +
                                searchViewModel.searchList.get(i).data!!.documents.get(j).placeName.substring(
                                    searchViewModel.searchList.get(i).data!!.documents.get(j).placeName.indexOf(" ")+1,
                                    searchViewModel.searchList.get(i).data!!.documents.get(j).placeName.indexOf("방향")
                                )
                                + ")"
                        )

                        directionHashSet.add(LatLng(
                            searchViewModel.searchList.get(i).data!!.documents.get(j).y.toDouble(),
                            searchViewModel.searchList.get(i).data!!.documents.get(j).x.toDouble())
                        )
                    }
                }
            }

            Log.i("TAG", "onCreate: "+ nameHashSet)
            directionViewModel.updateMarkers(directionHashSet)
//            Log.i("TAG", "onCreate: "+ directionHashSet)
        })
    }
}