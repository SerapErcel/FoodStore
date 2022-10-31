package com.serapercel.foodstore

import android.util.Log
import com.serapercel.foodstore.data.entity.CartFood
import com.serapercel.foodstore.data.entity.User



fun removeCount(sepet_yemek_id: Int, kullanici_adi: String) {
    Log.e("yemek ekle", "$sepet_yemek_id $kullanici_adi")
}

fun addCount(sepet_yemek_id: Int, kullanici_adi: String) {
    Log.e("yemek ekle", "$sepet_yemek_id $kullanici_adi")
}
val cartFood1 = CartFood(1, "Somon", "blabla",10,2,"serap")
val cartFood2 = CartFood(2, "Köfte", "blabla",20,2,"serap")
val cartFood3 = CartFood(3, "Balık", "blabla",30,2,"serap")




val userSerap = User(1, "Serap", "123456")