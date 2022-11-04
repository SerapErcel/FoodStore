package com.serapercel.foodstore

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import com.serapercel.foodstore.utils.user


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user.user_name = intent.getStringExtra("user_name").toString()

        val sharedPref = this.getSharedPreferences("user", Context.MODE_PRIVATE)
        user.user_name = sharedPref.getString("user_name", user.user_name).toString()
    }
}