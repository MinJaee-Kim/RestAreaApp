package com.minjaee.restareaapp.data.model.getdirection


import com.google.gson.annotations.SerializedName

data class Start(
    @SerializedName("location")
    val location: List<Double>
)