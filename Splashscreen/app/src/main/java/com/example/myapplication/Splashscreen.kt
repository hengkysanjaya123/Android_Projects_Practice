package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.timerTask

class Splashscreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen_activity)

//       doSplashscreenTimer()
        doSplashscreenHandler()
    }

    fun doSplashscreenHandler(){
        Handler().postDelayed(timerTask {
            val intt = Intent(this@Splashscreen, MainActivity::class.java)
            startActivity(intt)
        }, 2000)
    }

    fun doSplashscreenTimer(){
        Timer().schedule(timerTask {
            val intt = Intent(this@Splashscreen, MainActivity::class.java)
            startActivity(intt)
        }, 2000)
    }
}
