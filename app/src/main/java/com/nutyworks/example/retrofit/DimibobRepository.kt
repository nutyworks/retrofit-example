package com.nutyworks.example.retrofit

import android.util.Log
import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class DimibobRepository {

    companion object {
        private const val TAG = "DimibobRepository"
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.dimigo.in/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(DimigoinService::class.java)

    @WorkerThread
    suspend fun getMeal(date: String): MealResult = withContext(Dispatchers.IO) {
        try {
            MealResult.Success(service.mealWithDate(date))
        } catch (e: IOException) {
            Log.e(TAG, "NETWORKING EXCEPTION", e)
            MealResult.Failure(e.message)
        } catch (e: RuntimeException) {
            Log.e(TAG, "RUNTIME EXCEPTION", e)
            MealResult.Failure(e.message)
        } catch (e: Exception) {
            Log.e(TAG, "UNEXPECTED EXCEPTION", e)
            MealResult.Failure(e.message)
        }
    }
}

sealed class MealResult {
    class Success(val data: Meal) : MealResult()
    class Failure(val reason: Any?) : MealResult()
}
