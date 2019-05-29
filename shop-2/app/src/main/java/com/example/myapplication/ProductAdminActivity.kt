package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.myapplication.Helper.BASE_URL
import com.example.myapplication.Helper.CORE_TOKEN
import com.example.myapplication.Http.send
import com.example.myapplication.Models.Product
import kotlinx.android.synthetic.main.activity_product_admin.*
import kotlinx.android.synthetic.main.custom_product_admin_layout.view.*
import org.json.JSONObject

class ProductAdminActivity : AppCompatActivity() {

    val listProduct = ArrayList<Product>()
    var myProductAdminAdapter: ProductAdminAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_admin)

        myProductAdminAdapter = ProductAdminAdapter(this@ProductAdminActivity, listProduct, this@ProductAdminActivity)
        admin_listview.adapter = myProductAdminAdapter

        load()
    }

    fun load() {

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

                listProduct.clear()

                val obj = JSONObject(result)
                var data = obj.getJSONArray("data")
                for (i in 0 until data.length()) {
                    val objPer = data.getJSONObject(i)
                    listProduct.add(
                        Product(
                            objPer.getString("id"),
                            objPer.getString("name"),
                            objPer.getString("price"),
                            objPer.getString("stock")
                        )
                    )
                }

                myProductAdminAdapter?.notifyDataSetChanged()

            }
        }.execute("$BASE_URL/v1/products", "", "GET", CORE_TOKEN)
    }

    class ProductAdminAdapter(
        var context: Context, var list: ArrayList<Product>,
        var activity: ProductAdminActivity
    ) : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view = LayoutInflater.from(context).inflate(R.layout.custom_product_admin_layout, parent, false)

            val obj = getItem(position)
            view.tvName.text = obj.name
            view.tvPrice.text = obj.price
            view.tvStock.text = obj.stock
            view.iv_product_admin_del.tag = obj
            view.setOnClickListener {
                val intt = Intent(context, UpdateProductActivity::class.java)
                intt.putExtra("obj_product", obj)

                activity.startActivity(intt)
            }
            view.iv_product_admin_del.setOnClickListener {


            }


            return view
        }

        override fun getItem(position: Int): Product = list[position]

        override fun getItemId(position: Int): Long = 0

        override fun getCount(): Int = list.size

    }
}
