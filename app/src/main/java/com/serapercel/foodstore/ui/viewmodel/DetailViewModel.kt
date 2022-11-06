package com.serapercel.foodstore.ui.viewmodel

import android.util.Log
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
class DetailViewModel @Inject constructor(var frepo: FoodRepository) : ViewModel() {

    fun addCartList(user: User, yemek_siparis_adet: Int, food: Food) {

        CoroutineScope(Dispatchers.Main).launch {
            Log.e("hataa", " detail view model "+com.serapercel.foodstore.utils.user.user_name)

            val list = frepo.getCartFoods(user.user_name)
            var check = true
            for (cartFood in list) {
                if (cartFood.yemek_adi == food.yemek_adi) {
                    check = false
                    Log.e("hataa", " detail view model cartFood.yemek_adi == food.yemek_adi "+com.serapercel.foodstore.utils.user.user_name)

                    frepo.updateCartFood(user, yemek_siparis_adet, cartFood)
                }
            }
            if (check) {
                Log.e("hataa", " detail view model check "+com.serapercel.foodstore.utils.user.user_name)
                frepo.addCartList(user, yemek_siparis_adet, food)
            }

        }
    }
}