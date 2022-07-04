package com.minjaee.restareaapp.data.model.restarearoom


import com.google.gson.annotations.SerializedName

data class RestAreaRoom(
    @SerializedName("code")
    val code: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("list")
    val list: List<RoomResults>,
    @SerializedName("message")
    val message: String,
    @SerializedName("numOfRows")
    val numOfRows: Int,
    @SerializedName("pageNo")
    val pageNo: Int,
    @SerializedName("pageSize")
    val pageSize: Int
)