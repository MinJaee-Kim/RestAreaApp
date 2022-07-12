package com.minjaee.restareaapp.data.model.keywordsearch


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "search"
)
data class SearchMap(
    @PrimaryKey(autoGenerate = true)
    val index: Int? = null,
    @SerializedName("documents")
    val documents: List<Documents>,
    @SerializedName("meta")
    val meta: Meta,
)