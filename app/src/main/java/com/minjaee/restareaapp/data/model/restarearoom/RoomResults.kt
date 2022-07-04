package com.minjaee.restareaapp.data.model.restarearoom


import com.google.gson.annotations.SerializedName

data class RoomResults (
    @SerializedName("brand")
    val brand: String,
    @SerializedName("convenience")
    val convenience: String,
    @SerializedName("direction")
    val direction: Any,
    @SerializedName("maintenanceYn")
    val maintenanceYn: String,
    @SerializedName("numOfRows")
    val numOfRows: Any,
    @SerializedName("pageNo")
    val pageNo: Any,
    @SerializedName("routeCode")
    val routeCode: String,
    @SerializedName("routeName")
    val routeName: String,
    @SerializedName("serviceAreaCode")
    val serviceAreaCode: String,
    @SerializedName("serviceAreaCode2")
    val serviceAreaCode2: String,
    @SerializedName("serviceAreaName")
    val serviceAreaName: String,
    @SerializedName("svarAddr")
    val svarAddr: String,
    @SerializedName("telNo")
    val telNo: String,
    @SerializedName("truckSaYn")
    val truckSaYn: String
)