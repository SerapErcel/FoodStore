package com.serapercel.foodstore.data.datasource

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.serapercel.foodstore.data.entity.CartFood
import com.serapercel.foodstore.data.entity.Food
import com.serapercel.foodstore.data.entity.User
import com.serapercel.foodstore.retrofit.FoodDAO
import com.serapercel.foodstore.url
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
        Log.e("yemek", "data source ${fdao.getFoods().yemekler.toString()}")
        fdao.getFoods().yemekler
    }

    suspend fun updateCartFood(user: User, yemek_siparis_adet: Int, cartFood: CartFood){
        removeFood(cartFood.sepet_yemek_id, user.user_name)
        val tempFood = Food(cartFood.sepet_yemek_id.toString(), cartFood.yemek_adi , cartFood.yemek_resim_adi ,cartFood.yemek_fiyat.toString())
        addCartList(user, yemek_siparis_adet, tempFood)
    }

    suspend fun getCartFoods(kullanici_adi: String): List<CartFood> =
        withContext(Dispatchers.IO) { fdao.getCartFoods(kullanici_adi).sepet_yemekler }


    suspend fun searchFood(searchWord: String): List<Food> = withContext(Dispatchers.IO) {
        val foodList = ArrayList<Food>()
        val food = Food("1", "Izgara Somon", "blabla", "35tl")
        val food1 = Food("1", "Izgara Köfte", "blabla", "45tl")
        foodList.add(food)
        foodList.add(food1)
        Log.e("yemek", "data source search food ${foodList.toString()}")
        return@withContext foodList.toList()
    }
    suspend fun sortedFoods(): List<Food> = withContext(Dispatchers.IO){
        var foodlist =  getFoods().sortedWith(compareBy { it.yemek_fiyat.toInt() })
        Log.e("yemek", "data source ${foodlist.toString()}")
        return@withContext foodlist
    }

    suspend fun showImage(imageName:String, context: Context, view: ImageView) = withContext(Dispatchers.IO){
           /* Glide.with(context)
                .asBitmap()
                .load("$url$imageName")
                .override(225, 225)
                .into(BitmapImageViewTarget(view))*/
        Glide.with(context)
            .asBitmap()
            .load("$url$imageName")
            .override(225, 225)
            .into(object : BitmapImageViewTarget(view) {
                override fun setResource(resource: Bitmap?) {
                    //Play with bitmap
                    super.setResource(resource)
                }
            })
    }
}