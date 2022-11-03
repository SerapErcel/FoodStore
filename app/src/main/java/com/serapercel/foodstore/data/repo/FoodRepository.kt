package com.serapercel.foodstore.data.repo

import android.content.Context
import android.widget.ImageView
import com.serapercel.foodstore.data.datasource.FoodDatasource
import com.serapercel.foodstore.data.entity.CartFood
import com.serapercel.foodstore.data.entity.Food
import com.serapercel.foodstore.data.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodRepository(var fds: FoodDatasource) {

    suspend fun addCartList(user: User, yemek_siparis_adet: Int, food: Food) =
        fds.addCartList(user, yemek_siparis_adet, food)

    suspend fun removeFood(sepet_yemek_id: Int, kullanici_adi: String) =
        fds.removeFood(sepet_yemek_id, kullanici_adi)

    suspend fun getFoods(): List<Food> = fds.getFoods()

    suspend fun getCartFoods(kullanici_adi: String): List<CartFood> =
        fds.getCartFoods(kullanici_adi)

    suspend fun searchFood(searchWord: String): List<Food> = fds.searchFood(searchWord)

    suspend fun sortedFoods(): List<Food> = fds.sortedFoods()

    suspend fun showImage(imageName:String, context: Context, view: ImageView) = fds.showImage(imageName, context, view)


}
