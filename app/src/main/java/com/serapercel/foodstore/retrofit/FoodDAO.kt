package com.serapercel.foodstore.retrofit

import com.serapercel.foodstore.data.entity.Foods
import retrofit2.http.GET

interface FoodDAO {
    // http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getFoods(): Foods
}