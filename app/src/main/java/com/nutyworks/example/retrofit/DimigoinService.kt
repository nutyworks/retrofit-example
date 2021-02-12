package com.nutyworks.example.retrofit

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DimigoinService {
    @GET("meal/date/{date}")
    /**
     * @param date yyyy-MM-dd formatted date
     */
    suspend fun mealWithDate(@Path("date") date: String): Meal
}

data class Meal(val meal: Content) {
    data class Content(
        val breakfast: List<String>,
        val lunch: List<String>,
        val dinner: List<String>,
        val date: String,
    )
}
