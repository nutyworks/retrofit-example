package com.nutyworks.example.retrofit

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private val mViewModel =
        ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEventHandlers()

        mViewModel.fetchMeal()
    }

    private fun setupEventHandlers() {
        mViewModel.event.observe(this) { event ->
            when (event) {
                is Event.MealFetched -> ::updateMeal
            }()
        }
    }

    private fun updateMeal() {
        when (val result = mViewModel.meal.value) {
            is MealResult.Success ->
                findViewById<TextView>(R.id.bob).text = result.data.toString()
            is MealResult.Failure ->
                Toast.makeText(applicationContext, result.reason.toString(), Toast.LENGTH_LONG)
                    .show()
        }
    }

    sealed class Event {
        object MealFetched : Event()
    }
}