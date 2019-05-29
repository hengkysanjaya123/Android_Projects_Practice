package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.setBackgroundColor(Color.GREEN)
        textView.setBackgroundColor(resources.getColor(R.color.colorAccent))


//        textView.height = resources.getDimensionPixelOffset(R.dimen.wide_50)
        textView.height = resources.getDimension(R.dimen.wide_50).toInt()
    }
}
