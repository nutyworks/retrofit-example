package com.nutyworks.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.dimigo.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(DimigoinService::class.java)

        service.dimibobs("20201207").enqueue(object : Callback<Dimibob> {
            override fun onResponse(call: Call<Dimibob>, response: Response<Dimibob>) {
                val bobTextView = findViewById<TextView>(R.id.bob)
                response.body()?.let {
                    bobTextView.text = "아침: ${it.breakfast}\n점심: ${it.lunch}\n저녁: ${it.dinner}"
                }
            }

            override fun onFailure(call: Call<Dimibob>, t: Throwable) {
                Log.w("MA_s_d", "Dimibob request failed.", t)
            }
        })
    }
}