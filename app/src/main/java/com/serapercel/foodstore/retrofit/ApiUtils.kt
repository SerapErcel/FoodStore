package com.serapercel.foodstore.retrofit

class ApiUtils {
    companion object{
        var BASE_URL ="http://kasimadalan.pe.hu/"

        fun getFoodDao() : FoodDAO{
            return RetrofitClient.getClient(BASE_URL).create(FoodDAO::class.java)
        }
    }
}