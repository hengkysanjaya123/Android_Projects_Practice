package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.myapplication.Helper.BASE_URL
import com.example.myapplication.Helper.TOKEN
import com.example.myapplication.Helper.doAsync
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.net.URL


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFelineGreat.setOnClickListener{
            val intt = Intent(this, Im_Feline_Great_Activity::class.java)
            startActivity(intt)
        }

        button2.setOnClickListener{
            val intt = Intent(this, DetailActivity::class.java)
            startActivity(intt)
        }

        button3.setOnClickListener {
            val intt = Intent(this, UploadCatActivity::class.java)
            startActivity(intt)
        }
    }

    override fun onResume() {
        super.onResume()
        load()
    }

    fun load() {
        Log.d("test", "enter")
        doAsync("${BASE_URL}/v1/images/search", "GET", "", "${TOKEN}") {
            //            message(this@MainActivity, it)
            val array = JSONArray(it)

            val obj = array.getJSONObject(0)

            object : AsyncTask<String, Int, Bitmap>() {
                override fun onPreExecute() {
                    super.onPreExecute()
                    pbLoading.visibility = View.VISIBLE
                }

                override fun doInBackground(vararg params: String): Bitmap {
                    val url = URL(obj.getString("url"))
                    val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                    return bmp;
                }

                override fun onPostExecute(result: Bitmap) {
                    super.onPostExecute(result)
                    imageView.setImageBitmap(result)
                    pbLoading.visibility = View.GONE
                }
            }.execute("")
        }
    }
}
