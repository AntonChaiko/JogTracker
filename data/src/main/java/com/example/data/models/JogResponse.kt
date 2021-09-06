package com.example.data.models

import com.google.gson.annotations.SerializedName

data class JogRequest(
    @SerializedName("distance")
    val distance: Float,
    @SerializedName("time")
    val time: Int,
    @SerializedName("date")
    val date: String
)


data class JogsResponse(
    val response: AllJogsResponse
)

data class AllJogsResponse(
    @SerializedName("jogs")
    val jogs : List<JogResponse>,
)

data class JogResponse(

    @SerializedName("user_id")
    val userId: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("distance")
    val distance: Float,
    @SerializedName("time")
    val time: Int,
    @SerializedName("date")
    val date: String
)
data class JogUpdateRequest(

    @SerializedName("user_id")
    val userId: String,
    @SerializedName("jog_id")
    val id: Int,
    @SerializedName("distance")
    val distance: Float,
    @SerializedName("time")
    val time: Int,
    @SerializedName("date")
    val date: String
)