package com.serapercel.foodstore.data.entity

import java.io.Serializable

data class Food(
    var yemek_id: String,
    var yemek_adi: String,
    var yemek_resim_adi: String,
    var yemek_fiyat: String
) :
    Serializable
