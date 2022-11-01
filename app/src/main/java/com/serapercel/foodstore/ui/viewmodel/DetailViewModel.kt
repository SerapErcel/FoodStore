package com.serapercel.foodstore.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.serapercel.foodstore.data.entity.Food
import com.serapercel.foodstore.data.entity.User
import com.serapercel.foodstore.data.repo.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var frepo : FoodRepository): ViewModel(){

    fun addCartList(user: User, yemek_siparis_adet: Int, food: Food) {
        CoroutineScope(Dispatchers.Main).launch {
            frepo.addCartList(user, yemek_siparis_adet, food)
        }
    }
}