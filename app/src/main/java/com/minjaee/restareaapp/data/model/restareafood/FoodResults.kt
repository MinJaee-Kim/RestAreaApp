package com.minjaee.restareaapp.data.model.restareafood


import com.google.gson.annotations.SerializedName

data class FoodResults (
    @SerializedName("app")
    val app: String,
    @SerializedName("bestfoodyn")
    val bestfoodyn: String,
    @SerializedName("etc")
    val etc: String,
    @SerializedName("foodCost")
    val foodCost: String,
    @SerializedName("foodMaterial")
    val foodMaterial: String,
    @SerializedName("foodNm")
    val foodNm: String,
    @SerializedName("lastDtime")
    val lastDtime: String,
    @SerializedName("lastId")
    val lastId: String,
    @SerializedName("lsttmAltrDttm")
    val lsttmAltrDttm: String,
    @SerializedName("lsttmAltrUser")
    val lsttmAltrUser: String,
    @SerializedName("numOfRows")
    val numOfRows: Any,
    @SerializedName("pageNo")
    val pageNo: Any,
    @SerializedName("premiumyn")
    val premiumyn: String,
    @SerializedName("recommendyn")
    val recommendyn: String,
    @SerializedName("restCd")
    val restCd: String,
    @SerializedName("routeCd")
    val routeCd: String,
    @SerializedName("routeNm")
    val routeNm: String,
    @SerializedName("seasonMenu")
    val seasonMenu: String,
    @SerializedName("seq")
    val seq: String,
    @SerializedName("stdRestCd")
    val stdRestCd: String,
    @SerializedName("stdRestNm")
    val stdRestNm: String,
    @SerializedName("svarAddr")
    val svarAddr: String
)