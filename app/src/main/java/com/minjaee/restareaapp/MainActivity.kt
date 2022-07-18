package com.minjaee.restareaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    @Inject
    lateinit var restAreaFactory: RestAreaViewModelFactory

    lateinit var directionViewModel: DirectionViewModel
    lateinit var searchViewModel: SearchViewModel
    lateinit var restAreaViewModel: RestAreaViewModel

    private lateinit var binding: ActivityMainBinding

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
        restAreaViewModel = ViewModelProvider(this, restAreaFactory)
            .get(RestAreaViewModel::class.java)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        viewModelObserver()
    }

    private fun viewModelObserver() {
        searchViewModel.provideListener.observe(this, Observer {
            for (i in searchViewModel.searchList.indices){
                for (j in searchViewModel.searchList.get(i).data?.documents?.indices!!) {
                    if (searchViewModel.searchList.get(i).data!!.documents.get(j).categoryName.endsWith("고속도로휴게소")){
                        if (searchViewModel.searchList.get(i).data!!.documents.get(j).placeName.equals("방향")) {
                            nameHashSet.add(
                                searchViewModel.searchList.get(i).data!!.documents.get(j).placeName.substring(
                                    0,
                                    searchViewModel.searchList.get(i).data!!.documents.get(j).placeName.indexOf(
                                        "휴게소"
                                    )
                                ) + "(" +
                                        searchViewModel.searchList.get(i).data!!.documents.get(j).placeName.substring(
                                            searchViewModel.searchList.get(i).data!!.documents.get(j).placeName.indexOf(
                                                " "
                                            ) + 1,
                                            searchViewModel.searchList.get(i).data!!.documents.get(j).placeName.indexOf(
                                                "방향"
                                            )
                                        )
                                        + ")"
                            )
                        } else {
                            nameHashSet.add(
                                searchViewModel.searchList.get(i).data!!.documents.get(j).placeName.substring(
                                    0,
                                    searchViewModel.searchList.get(i).data!!.documents.get(j).placeName.indexOf(
                                        "휴게소"
                                    )
                                )
                            )
                        }

                        directionHashSet.add(LatLng(
                            searchViewModel.searchList.get(i).data!!.documents.get(j).y.toDouble(),
                            searchViewModel.searchList.get(i).data!!.documents.get(j).x.toDouble())
                        )
                    }
                }
            }

            searchViewModel.updateLocationHashSet(nameHashSet)
            directionViewModel.updateMarkers(directionHashSet)
        })
    }
}