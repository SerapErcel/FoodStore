package com.serapercel.foodstore

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.serapercel.foodstore.data.entity.User

val user = User(1, "Serap", "123456")

val url = "http://kasimadalan.pe.hu/yemekler/resimler/"

fun showImage(imageName:String, context: Context, view: ImageView){
    Glide.with(context).load("$url$imageName").override(225, 225).into(view)
}