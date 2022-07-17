package com.minjaee.restareaapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minjaee.restareaapp.data.model.keywordsearch.Documents
import com.minjaee.restareaapp.data.model.keywordsearch.SearchMap
import com.minjaee.restareaapp.databinding.SearchListItemBinding

class SearchAdapter:RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var searchList: SearchMap? = null

    fun setList(search: SearchMap?){
        searchList = search
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = SearchListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchList?.documents?.get(position))
    }

    override fun getItemCount(): Int {
        if (searchList != null) {
            return searchList!!.documents.size
        } else {
            return 0
        }
    }

    inner class SearchViewHolder(
        val binding: SearchListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(documents: Documents?) {
            if (documents != null) {
                binding.tvName.text = documents.placeName
                binding.tvAddress.text = documents.addressName

                binding.root.setOnClickListener {
                    onItemClickListener?.let {
                        it(documents)
                    }
                }
            }
        }
    }

    private var onItemClickListener :((Documents)->Unit)?=null

    fun setOnItemClickListener(listener: (Documents)->Unit){
        onItemClickListener = listener
    }
}