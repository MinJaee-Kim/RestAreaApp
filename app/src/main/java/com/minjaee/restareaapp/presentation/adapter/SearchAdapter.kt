package com.minjaee.restareaapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap
import com.minjaee.restareaapp.databinding.SearchListItemBinding

class SearchAdapter:RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private val searchList = ArrayList<SearchMap>()

    fun setList(search: List<SearchMap>){
        searchList.clear()
        searchList.addAll(search)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = SearchListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchList[position], position)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    inner class SearchViewHolder(
        val binding: SearchListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(searchMap: SearchMap, position: Int) {
            binding.tvName.text = searchMap.documents[position].addressName
            binding.tvAddress.text = searchMap.documents[position].roadAddressName
        }
    }
}