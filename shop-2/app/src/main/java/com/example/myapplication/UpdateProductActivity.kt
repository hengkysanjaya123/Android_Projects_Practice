package com.example.myapplication

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.Helper.BASE_URL
import com.example.myapplication.Helper.CORE_TOKEN
import com.example.myapplication.Helper.message
import com.example.myapplication.Http.send
import com.example.myapplication.Models.Product
import kotlinx.android.synthetic.main.activity_update_product.*
import kotlinx.android.synthetic.main.custom_product_admin_layout.*
import org.json.JSONObject

class UpdateProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_product)

        val objProduct = intent.getSerializableExtra("obj_product") as Product
        tvTitle.text = objProduct.name
        tvName.text = objProduct.name
        tvPrice.text = objProduct.price
        tvStock.text = objProduct.stock

        btnSubmit.setOnClickListener{
            var params = JSONObject()
            params.put("name", objProduct.name)
            params.put("price", objProduct.price)
            params.put("stock", objProduct.stock)

            object : AsyncTask<String, Int, String>() {

                override fun onPreExecute() {
                    super.onPreExecute()
                }

                @SuppressLint("WrongThread")
                override fun doInBackground(vararg params: String): String {
                    return send(params[0], params[1], params[2], params[3])
                }

                override fun onPostExecute(result: String) {
                    super.onPostExecute(result)

                    if(result.contains("message")){
                        val obj = JSONObject(result)
                        val msg = obj.getString("message")
                        message(this@UpdateProductActivity, msg)
                    }else{

                    }

                }
            }.execute("$BASE_URL/v1/products/${objProduct.id}", "${params.toString()}", "PUT", CORE_TOKEN)
        }
    }
}
