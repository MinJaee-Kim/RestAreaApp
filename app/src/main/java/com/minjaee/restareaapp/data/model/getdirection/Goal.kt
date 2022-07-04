package com.minjaee.restareaapp.data.model.getdirection


import com.google.gson.annotations.SerializedName

data class Goal(
    @SerializedName("dir")
    val dir: Int,
    @SerializedName("location")
    val location: List<Double>
)