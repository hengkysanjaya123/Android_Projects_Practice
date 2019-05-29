package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.myapplication.DB.DatabaseHelper
import com.example.myapplication.Model.Cat
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.custom_detail_image.view.*
import java.net.URL

class DetailActivity : AppCompatActivity() {

    var listFavourites: List<Cat> = arrayListOf<Cat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dbHelper = DatabaseHelper(this)

        listFavourites = dbHelper.getFavourites()

        val value = intent.getStringExtra("id")
        val byteArray = intent.getByteArrayExtra("image")
        val objCat = intent.getSerializableExtra("obj_cat")


        val linear = LinearLayout(this)
        linear.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        linear.orientation = LinearLayout.VERTICAL
        linear.setPadding(24,24,24,24)

        if (value != null) {
            val view = LayoutInflater.from(this).inflate(R.layout.custom_detail_image, null)
            view.imageView10.setImageBitmap(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size))

            if (listFavourites.find {
                    it.id == (objCat as Cat).id
                } != null) {
                view.switch1.isChecked = true
            } else {
                view.switch1.isChecked = false
            }

            view.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    dbHelper.addFavourites(objCat as Cat)
                } else {
                    dbHelper.deleteCat((objCat as Cat))
                }
            }
            linear.addView(view)
        } else {
            Log.d("test", listFavourites.size.toString())
            for (cat in listFavourites) {
                object : AsyncTask<String, Int, Bitmap>() {

                    @SuppressLint("WrongThread")
                    override fun doInBackground(vararg params: String?): Bitmap {
                        val url = URL(cat.url)
                        val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                        return bitmap
                    }

                    override fun onPostExecute(result: Bitmap) {
                        super.onPostExecute(result)
                        val view = LayoutInflater.from(this@DetailActivity).inflate(R.layout.custom_detail_image, null)
                        view.setPadding(0, 8, 0, 0)
                        view.imageView10.setImageBitmap(result)
                        view.switch1.isChecked = true
                        view.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
                            if (isChecked) {
                                dbHelper.addFavourites(cat)
                            } else {
                                dbHelper.deleteCat(cat)
//                                linear.removeView(view)
                            }
                        }
                        linear.addView(view)
                    }
                }.execute("")
            }
        }

        scroll_view_activity_detail.addView(linear)
    }
}
