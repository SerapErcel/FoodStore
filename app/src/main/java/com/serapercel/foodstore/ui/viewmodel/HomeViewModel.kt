package com.serapercel.foodstore.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serapercel.foodstore.data.entity.Food
import com.serapercel.foodstore.data.repo.FoodRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val frepo = FoodRepository()
    var foodList = MutableLiveData<List<Food>>()

    init {
        getFoods()
    }

    fun getFoods() {
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = frepo.getFoods()
        }
    }

    fun searchFood(searchWord: String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = frepo.searchFood(searchWord)
        }
    }
}