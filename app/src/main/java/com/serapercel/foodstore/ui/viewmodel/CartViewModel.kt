package com.serapercel.foodstore.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serapercel.foodstore.data.entity.CartFood
import com.serapercel.foodstore.data.entity.User
import com.serapercel.foodstore.data.repo.FoodRepository
import com.serapercel.foodstore.utils.user
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var frepo: FoodRepository) : ViewModel() {
    var cartList = MutableLiveData<List<CartFood>>()

    init {
        getCartFoods()
    }

    fun getCartFoods() {
        CoroutineScope(Dispatchers.Main).launch {
            if (frepo.getCartFoods(user.user_name).isEmpty()){

            }else {
                cartList.value = frepo.getCartFoods(user.user_name)
            }
        }
    }

    fun removeFood(sepet_yemek_id: Int, kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            frepo.removeFood(sepet_yemek_id, kullanici_adi)
            getCartFoods()
        }
    }

    fun calculatePrice(): Int {
        var price = 0
        return if (cartList.value == null) {
            price
        } else {
            for (f in cartList.value!!) {
                price += f.yemek_fiyat!! * f.yemek_siparis_adet!!
            }
            price
        }
    }

}