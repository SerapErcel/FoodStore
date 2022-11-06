package com.serapercel.foodstore.data.datasource

import com.serapercel.foodstore.data.entity.CartFood
import com.serapercel.foodstore.data.entity.Food
import com.serapercel.foodstore.data.entity.User
import com.serapercel.foodstore.retrofit.FoodDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class FoodDatasource(var fdao: FoodDAO) {

    suspend fun addCartList(user: User, yemek_siparis_adet: Int, food: Food) {
        fdao.addCart(
            food.yemek_adi,
            food.yemek_resim_adi,
            food.yemek_fiyat.toInt(),
            yemek_siparis_adet,
            user.user_name
        )
    }

    suspend fun removeFood(sepet_yemek_id: Int, kullanici_adi: String) =
        fdao.deleteCart(sepet_yemek_id, kullanici_adi)

    suspend fun getFoods(): List<Food> = withContext(Dispatchers.IO) {
        fdao.getFoods().yemekler
    }

    suspend fun updateCartFood(user: User, yemek_siparis_adet: Int, cartFood: CartFood) {
        val count = cartFood.yemek_siparis_adet?.plus(yemek_siparis_adet)
        removeFood(cartFood.sepet_yemek_id, user.user_name)
        val food = Food(
            cartFood.sepet_yemek_id.toString(),
            cartFood.yemek_adi,
            cartFood.yemek_resim_adi,
            cartFood.yemek_fiyat.toString()
        )
        if (count != null) {
            addCartList(user, count, food)
        }
    }

    suspend fun getCartFoods(kullanici_adi: String): List<CartFood> =
        withContext(Dispatchers.IO) {
            return@withContext fdao.getCartFoods(kullanici_adi).sepet_yemekler
        }

}