package com.minjaee.restareaapp.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minjaee.restareaapp.data.model.restarearoom.RestAreaRoom
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.databinding.ExploreListItemBinding

class RoomsAdapter:RecyclerView.Adapter<RoomsAdapter.RoomsViewHolder>() {
    private lateinit var roomsList:ArrayList<Resource<RestAreaRoom>>

    fun setList(roomsList: ArrayList<Resource<RestAreaRoom>>){
        this.roomsList = roomsList
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
            binding.areaConvenience.text = restAreaRoom.get(position).data?.list?.get(0)?.convenience
        }
    }

    private var onItemClickListener :((RestAreaRoom)->Unit)?=null

    fun setOnItemClickListener(listener: (RestAreaRoom)->Unit){
        onItemClickListener = listener
    }
}