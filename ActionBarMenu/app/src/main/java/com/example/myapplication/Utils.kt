package com.example.myapplication

import android.content.Context
import android.widget.Toast

fun msg(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}