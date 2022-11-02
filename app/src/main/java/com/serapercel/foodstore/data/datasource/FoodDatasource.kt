package com.serapercel.foodstore.data.datasource

import android.util.Log
import com.serapercel.foodstore.data.entity.CartFood
import com.serapercel.foodstore.data.entity.Food
import com.serapercel.foodstore.data.entity.User
import com.serapercel.foodstore.retrofit.FoodDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDatasource(var fdao: FoodDAO) {

    suspend fun addCartList(user: User, yemek_siparis_adet: Int, food: Food) {
        if (fdao.addCart(
                food.yemek_adi,
                food.yemek_resim_adi,
                food.yemek_fiyat.toInt(),
                yemek_siparis_adet,
                user.user_name
            ).success == 1
        ) {
            Log.e("yemek", "${user.user_name} ${food.yemek_adi}")
        } else if (fdao.addCart(
                food.yemek_adi,
                food.yemek_resim_adi,
                food.yemek_fiyat.toInt(),
                yemek_siparis_adet,
                user.user_name
            ).success == 0
        ) {
            Log.e("yemek", " Eklenemedi !!! ${user.user_name} ${food.yemek_adi}")
        }
    }

    suspend fun removeFood(sepet_yemek_id: Int, kullanici_adi: String) = fdao.deleteCart(sepet_yemek_id, kullanici_adi)

    suspend fun getFoods(): List<Food> = withContext(Dispatchers.IO) {
        fdao.getFoods().yemekler
    }

    suspend fun getCartFoods(kullanici_adi: String): List<CartFood> = withContext(Dispatchers.IO) {
        fdao.getCartFoods(kullanici_adi).yemekler
    }

    suspend fun searchFood(searchWord: String): List<Food> = withContext(Dispatchers.IO) {
        val foodList = ArrayList<Food>()
        val food = Food("1", "Izgara Somon", "blabla", "35tl")
        val food1 = Food("1", "Izgara Köfte", "blabla", "45tl")
        foodList.add(food)
        foodList.add(food1)
        return@withContext foodList
    }
}