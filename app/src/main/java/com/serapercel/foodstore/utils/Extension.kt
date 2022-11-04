package com.serapercel.foodstore.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.serapercel.foodstore.data.entity.User

var user = User(1, "Anonymous", "password")

val url = "http://kasimadalan.pe.hu/yemekler/resimler/"

fun ImageView.showImage(imageName:String, context: Context) {
    Glide.with(context).load("$url$imageName").into(this)
}

