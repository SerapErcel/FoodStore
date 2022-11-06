package com.serapercel.foodstore.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serapercel.foodstore.data.entity.Food
import com.serapercel.foodstore.data.repo.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var frepo: FoodRepository) : ViewModel() {
    var foodList = MutableLiveData<List<Food>>()

    init {
        getFoods()
    }

    fun getFoods() {
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = frepo.getFoods()
        }
    }


    fun sortFoodDecRising(list: List<Food>): List<Food> {
        return list.sortedByDescending { it.yemek_fiyat.toInt() }
    }

    fun sortFoodRising(list: List<Food>): List<Food> {
        return list.sortedBy {
            it.yemek_fiyat.toInt()
        }
    }

    fun searchFood(aramaKelimesi: String): List<Food> {
        val newList= ArrayList<Food>()
        for (food in foodList.value!!) {
            if (food.yemek_adi.lowercase().contains(aramaKelimesi.lowercase()) ) {
                newList.add(food)
            }
        }
        return newList.toList()
    }

}