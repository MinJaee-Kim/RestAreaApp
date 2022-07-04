package com.minjaee.restareaapp.data.model.getdirection


import com.google.gson.annotations.SerializedName

data class GetDirections(
    @SerializedName("code")
    val code: Int,
    @SerializedName("currentDateTime")
    val currentDateTime: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("route")
    val route: Route
)