package com.serapercel.foodstore.data.datasource

import android.util.Log
import com.serapercel.foodstore.data.entity.Food
import com.serapercel.foodstore.data.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDatasource {

    suspend fun addCartList(user: User, yemek_siparis_adet: Int, food: Food) {
        Log.e("yemek", " sepete yemek ekle $yemek_siparis_adet ${food.yemek_adi} ${user.user_name}")
    }

    suspend fun removeFood(sepet_yemek_id: Int, kullanici_adi: String) {
        Log.e("yemek sil", "$sepet_yemek_id $kullanici_adi")
    }

    suspend fun removeCount(sepet_yemek_id: Int, kullanici_adi: String) {
        Log.e("yemek ekle", "$sepet_yemek_id $kullanici_adi")
    }

    suspend fun addCount(sepet_yemek_id: Int, kullanici_adi: String) {
        Log.e("yemek ekle", "$sepet_yemek_id $kullanici_adi")
    }

    suspend fun getFoods(): List<Food> = withContext(Dispatchers.IO) {
        val foodList = ArrayList<Food>()
        val food = Food("1", "Izgara Somon", "blabla", "35tl")
        val food1 = Food("1", "Izgara Köfte", "blabla", "45tl")
        val food2 = Food("1", "Ayran", "blabla", "55tl")
        foodList.add(food)
        foodList.add(food1)
        foodList.add(food2)
        return@withContext foodList
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