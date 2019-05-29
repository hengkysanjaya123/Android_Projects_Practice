package com.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list_names = resources.getStringArray(R.array.array_images_name)
        val list_images = resources.obtainTypedArray(R.array.array_images)

        for (i in 0 until list_images.length()) {
            val imageView = ImageView(this)
            imageView.layoutParams = LinearLayout.LayoutParams(
                250,
                250
            )
            imageView.setImageDrawable(list_images.getDrawable(i))
            imageView.scaleType = ImageView.ScaleType.FIT_XY

            layout_parent.addView(imageView)
        }
    }
}
