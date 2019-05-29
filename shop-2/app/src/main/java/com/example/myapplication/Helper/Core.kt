package com.example.myapplication.Helper

import android.content.Context
import android.widget.Toast

//const val BASE_URL = "http://10.0.2.2"
const val BASE_URL = "http://10.22.212.116"
var CORE_TOKEN : String = ""

fun message(context : Context, msg : String){
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}