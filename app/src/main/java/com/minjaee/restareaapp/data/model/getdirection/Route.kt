package com.minjaee.restareaapp.data.model.getdirection


import com.google.gson.annotations.SerializedName

data class Route(
    @SerializedName("traoptimal")
    val traoptimal: List<Traoptimal>
)