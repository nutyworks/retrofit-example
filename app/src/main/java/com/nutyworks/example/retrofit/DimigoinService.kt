package com.nutyworks.example.retrofit

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DimigoinService {
    @GET("dimibobs/{date}")
    fun dimibobs(@Path("date") date: String): Call<Dimibob>
}

data class Dimibob(
    @SerializedName("breakfast") val breakfast: String,
    @SerializedName("lunch")     val lunch: String,
    @SerializedName("dinner")    val dinner: String,
    @SerializedName("snack")     val snack: String,
    @SerializedName("date")      val date: String,
)

