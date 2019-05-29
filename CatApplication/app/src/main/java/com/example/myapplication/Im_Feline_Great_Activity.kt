package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.myapplication.Helper.BASE_URL
import com.example.myapplication.Helper.TOKEN
import com.example.myapplication.Helper.doAsync
import com.example.myapplication.Model.Cat
import kotlinx.android.synthetic.main.activity_im__feline__great_.*
import kotlinx.android.synthetic.main.pattern_images_1.view.*
import org.json.JSONArray
import java.io.ByteArrayOutputStream
import java.net.URL
import kotlin.concurrent.thread

class Im_Feline_Great_Activity : AppCompatActivity() {

    lateinit var view_linear: LinearLayout
    var i = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_im__feline__great_)

        view_linear = LinearLayout(this)
        view_linear.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        view_linear.orientation = LinearLayout.VERTICAL

//        val v = LayoutInflater.from(this).inflate(R.layout.pattern_images_1, null)
//        val v2 = LayoutInflater.from(this).inflate(R.layout.pattern_images_2, null)
//        view_linear.addView(v)
//        view_linear.addView(v2)
        scroll_view.addView(view_linear)

//        scroll_view.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
//            val view =  view_linear.getChildAt(view_linear.childCount - 1)
//            var diff = (view_linear.bottom  - (view_linear.height + scrollY))
//            if(diff == 0){
//                Log.d("test", "Reached the bottom")
//            }
//        }

        load()
    }

    fun load() {
        var v: View? = null
        if (i % 2 == 0) {
            v = LayoutInflater.from(this).inflate(R.layout.pattern_images_1, null)
        } else {
            v = LayoutInflater.from(this).inflate(R.layout.pattern_images_2, null)
        }



        thread (start = true) { loadImage(v.imageView1) }
        thread (start = true) { loadImage(v.imageView2) }
        thread (start = true) { loadImage(v.imageView3) }
        thread (start = true) { loadImage(v.imageView4) }
        thread (start = true) { loadImage(v.imageView5) }
        thread (start = true) { loadImage(v.imageView6) }
        thread (start = true) { loadImage(v.imageView7) }
        thread (start = true) { loadImage(v.imageView8) }
        thread (start = true) { loadImage(v.imageView9) }

        view_linear.addView(v)
        i++
    }

    fun loadImage(iv: ImageView) {
        doAsync("${BASE_URL}/v1/images/search", "GET", "", "${TOKEN}") {

            val array = JSONArray(it)
            val obj = array.getJSONObject(0)
            val id = obj.getString("id")
            val strUrl = obj.getString("url")
            val objCat = Cat(id, strUrl)

            object : AsyncTask<String, Int, Bitmap>() {

                override fun onPreExecute() {
                    super.onPreExecute()
                }

                override fun doInBackground(vararg params: String?): Bitmap {
                    val url = URL(strUrl)
                    val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

                    Log.d("test", url.toString())


                    return bmp
                }

                override fun onPostExecute(result: Bitmap) {
                    super.onPostExecute(result)

                    iv.setImageBitmap(result)
                    iv.tag = objCat
                    iv.setOnClickListener {
                        val intt = Intent(this@Im_Feline_Great_Activity, DetailActivity::class.java)
                        intt.putExtra("id", id)

                        val bStream = ByteArrayOutputStream()
                        result.compress(Bitmap.CompressFormat.PNG, 100, bStream)
                        val byteArray = bStream.toByteArray()

                        intt.putExtra("image", byteArray)
                        intt.putExtra("obj_cat", objCat)

                        this@Im_Feline_Great_Activity.startActivity(intt)
                    }

                }
            }.execute("")
        }
    }
}
