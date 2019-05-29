package com.example.myapplication.Helper

import android.content.Context
import android.widget.Toast

class Core {
    companion object {
        var core_token : String = ""

        fun Message(context : Context, text: String){
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }
}