package com.example.myapplication.Helper

import android.content.Context
import android.widget.Toast

val BASE_URL : String = "http://10.22.212.232"

fun msg(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}