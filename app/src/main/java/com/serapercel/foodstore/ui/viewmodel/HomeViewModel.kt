package com.serapercel.foodstore.ui.viewmodel

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.serapercel.foodstore.data.entity.Food
import com.serapercel.foodstore.data.repo.FoodRepository
import com.serapercel.foodstore.url
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
            Log.e("yemek", "wiev model get foods ${foodList.toString()}")
        }
    }
    fun sortedFoods() {
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = frepo.sortedFoods()
            Log.e("yemek", "wiev model ${foodList.toString()}")
        }
    }

    fun searchFood(searchWord: String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = frepo.searchFood(searchWord)
        }
    }

    fun showImage(imageName:String, context: Context, view: ImageView){
        CoroutineScope(Dispatchers.Main).launch {
            frepo.showImage(imageName, context, view)
        }
    }



}