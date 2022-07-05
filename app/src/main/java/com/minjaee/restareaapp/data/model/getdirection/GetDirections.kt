package com.minjaee.restareaapp.data.model.getdirection


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "getdirections"
)
data class GetDirections(
    @SerializedName("code")
    val code: Int,
    @PrimaryKey(autoGenerate = true)
    @SerializedName("currentDateTime")
    val currentDateTime: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("route")
    val route: Route?
)