package com.minjaee.restareaapp.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.minjaee.restareaapp.HomeFragment
import com.minjaee.restareaapp.R
import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.databinding.ExploreListItemBinding
import com.minjaee.restareaapp.presentation.viewmodel.RestAreaViewModel

class RoomsAdapter(val context: Context):RecyclerView.Adapter<RoomsAdapter.RoomsViewHolder>() {
    private lateinit var roomsList:ArrayList<Resource<RestAreaRoom>>
    private lateinit var foodsList:ArrayList<Resource<RestAreaFood>>
    private lateinit var foodsAdapter: FoodsAdapter
    private var flipPosition = -1;

    fun setList(
        roomsList: ArrayList<Resource<RestAreaRoom>>,
        foodsList: ArrayList<Resource<RestAreaFood>>){
        this.roomsList = roomsList
        this.foodsList = foodsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        val binding = ExploreListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return RoomsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return roomsList.size
    }

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        holder.bind(roomsList, position)
    }

    inner class RoomsViewHolder(
        val binding: ExploreListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(restAreaRoom:ArrayList<Resource<RestAreaRoom>>, position: Int) {
            binding.areaName.text = restAreaRoom.get(position).data?.list?.get(0)?.serviceAreaName
            binding.areaBrand.text = restAreaRoom.get(position).data?.list?.get(0)?.brand
            binding.areaConvenience.text = "제공: " + restAreaRoom.get(position).data?.list?.get(0)?.convenience!!.
            substring(0, restAreaRoom.get(position).data?.list?.get(0)?.convenience!!.length-1).
            replace("|", ", ")

            binding.foodsRv.adapter = FoodsAdapter(foodsList, position)
            binding.foodsRv.layoutManager = LinearLayoutManager(context)


            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(restAreaRoom[position])
                }
                if (binding.foodsRv.visibility == View.GONE) {
                    binding.foodsRv.visibility = View.VISIBLE
                    binding.areaBtn.setImageResource(R.drawable.arrow_up)
                } else {
                    binding.foodsRv.visibility = View.GONE
                    binding.areaBtn.setImageResource(R.drawable.arrow_down)
                }
            }

            binding.areaBtn.setOnClickListener {
                //TODO 애니메이션
                if (binding.foodsRv.visibility == View.GONE) {
                    binding.foodsRv.visibility = View.VISIBLE
                    binding.areaBtn.setImageResource(R.drawable.arrow_up)
                } else {
                    binding.foodsRv.visibility = View.GONE
                    binding.areaBtn.setImageResource(R.drawable.arrow_down)
                }
            }
        }
    }

    private var onItemClickListener :((Resource<RestAreaRoom>)->Unit)?=null

    fun setOnItemClickListener(listener: (Resource<RestAreaRoom>)->Unit){
        onItemClickListener = listener
    }
}