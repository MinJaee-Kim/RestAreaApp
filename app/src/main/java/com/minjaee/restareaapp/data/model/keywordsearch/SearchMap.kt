package com.minjaee.restareaapp.data.model.keywordsearch


import com.google.gson.annotations.SerializedName

data class SearchMap(
    @SerializedName("documents")
    val documents: List<Document>,
    @SerializedName("meta")
    val meta: Meta
)