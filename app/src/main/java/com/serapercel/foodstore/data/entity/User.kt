package com.serapercel.foodstore.data.entity

import java.io.Serializable

data class User(
    var user_id: Int,
    var user_name: String,
    var password: String,
    //var cartList: ArrayList<CartFood>
) : Serializable

