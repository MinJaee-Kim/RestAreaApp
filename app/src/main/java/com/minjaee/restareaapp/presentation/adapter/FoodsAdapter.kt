package com.minjaee.restareaapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minjaee.restareaapp.data.model.restareafood.RestAreaFood
import com.minjaee.restareaapp.data.util.Resource
import com.minjaee.restareaapp.databinding.FoodsListItemBinding

class FoodsAdapter(
    private val foodsList:ArrayList<Resource<RestAreaFood>>,
    private val position:Int
    ): RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {
        val binding = FoodsListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return FoodsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {
        holder.bind(foodsList, this.position)
    }

    override fun getItemCount(): Int {
        return foodsList[position].data!!.list.size
    }

    inner class FoodsViewHolder(
        val binding: FoodsListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(foodsList: ArrayList<Resource<RestAreaFood>>, position: Int) {
            binding.foodName.text = foodsList.get(position).data!!.list[this.position].foodNm
            binding.foodCost.text = foodsList.get(position).data!!.list[this.position].foodCost
            binding.foodEtc.text = foodsList.get(position).data!!.list[this.position].etc
        }
    }
}