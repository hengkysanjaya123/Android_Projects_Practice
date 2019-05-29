package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("test", "onCreate was just called!")
    }

    override fun onResume() {
        super.onResume()
        Log.d("test", "onResume was just called!")
    }

    override fun onPause() {
        super.onPause()
        Log.d("test", "onPause was just called!")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("test", "onDestroy was just called!")
    }
}
