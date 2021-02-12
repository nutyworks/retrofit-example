package com.nutyworks.example.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
    }

    private val repository = DimibobRepository()

    private val _meal = MutableLiveData<MealResult>()
    val meal: LiveData<MealResult> = _meal

    private val _event = MutableLiveData<MainActivity.Event>()
    val event: LiveData<MainActivity.Event> = _event

    fun fetchMeal() = viewModelScope.launch {
        _meal.value = repository.getMeal("2021-02-12")
        emit(MainActivity.Event.MealFetched)
    }

    private fun emit(event: MainActivity.Event) {
        _event.value = event
    }
}

