package com.serapercel.foodstore.data.repo

import android.util.Log
import com.serapercel.foodstore.data.datasource.FoodDatasource
import com.serapercel.foodstore.data.entity.Food
import com.serapercel.foodstore.data.entity.User

class FoodRepository {
    var fds = FoodDatasource()

    suspend fun addCartList(user: User, yemek_siparis_adet: Int, food: Food) =
        fds.addCartList(user, yemek_siparis_adet, food)

    suspend fun removeFood(sepet_yemek_id: Int, kullanici_adi: String) =
        fds.removeFood(sepet_yemek_id, kullanici_adi)

    suspend fun removeCount(sepet_yemek_id: Int, kullanici_adi: String) =
        fds.removeCount(sepet_yemek_id, kullanici_adi)

    suspend fun addCount(sepet_yemek_id: Int, kullanici_adi: String) =
        fds.addCount(sepet_yemek_id, kullanici_adi)
}
