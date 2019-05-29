package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.Helper.Core
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        button2.setOnClickListener{
            Core.Message(this, Core.core_token)
        }
    }
}
