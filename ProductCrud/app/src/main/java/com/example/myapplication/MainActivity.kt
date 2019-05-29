package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.Helper.BASE_URL
import com.example.myapplication.Http.send
import com.example.myapplication.Models.Product
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_product_layout.view.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var productAdapter: ProductAdapter? = null
    var listProduct = ArrayList<Product>()
    var totalPage = 0
    var currentPage = -1
    var totalCount = 0
    var preLast: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productAdapter = ProductAdapter(this@MainActivity, listProduct, this@MainActivity)
        lvResult.adapter = productAdapter

        LoadData()

        btn.setOnClickListener {
            Log.d("test:Total Products", listProduct.distinctBy { it.id }.count().toString())
//            listProduct.add(Product(9999, "test", "1", 1, 1))
//            productAdapter!!.notifyDataSetChanged()
        }

        var listener = object : AbsListView.OnScrollListener {
            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                var lastItem = firstVisibleItem + visibleItemCount
                if (lastItem == totalItemCount) {
                    if(totalCount >= listProduct.count()){
                        LoadData()
                    }
                }
            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {

            }

        }
        lvResult.setOnScrollListener(listener)
    }

    override fun onDestroy() {
        super.onDestroy()
        listProduct.clear()
    }

    fun LoadData() {
        currentPage += 1
        var url = ""
        if (totalPage == 0) {
            url = "${BASE_URL}/produk"
        } else {
            url = "${BASE_URL}/v1/products?page=$currentPage"
        }
        object : AsyncTask<String, Int, String>() {

            override fun onPreExecute() {
                pbLoading.visibility = View.VISIBLE
                super.onPreExecute()
            }

            @SuppressLint("WrongThread")
            override fun doInBackground(vararg params: String): String {
                return send(params[0], params[1], params[2], params[3])
            }

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)

                pbLoading.visibility = View.GONE

                val jsonObj = JSONObject(result)
                val jsonArray = jsonObj.getJSONArray("items")
                val metaObj = jsonObj.getJSONObject("_meta")
                val pageCount = metaObj.getInt("pageCount")
                totalPage = pageCount
                totalCount = metaObj.getInt("totalCount")
                Log.d("test", metaObj.getInt("currentPage").toString())

                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    val id = obj.getInt("ID")
                    val name = obj.getString("Name")
                    val id_category = obj.getString("ID Category")
                    val price_customer = obj.getInt("Price Customer")
                    val price_sales = obj.getInt("Price Sales")

                    listProduct.add(Product(id, name, id_category, price_customer, price_sales))
                }

//                productAdapter = ProductAdapter(this@MainActivity, listProduct, this@MainActivity)
                productAdapter?.notifyDataSetChanged()
            }
        }.execute(url, "", "GET", "")
    }

    class ProductAdapter(
        var context: Context, var list: ArrayList<Product>,
        var activity: MainActivity
    ) : BaseAdapter() {

        data class ViewHolder(
            val tvName: TextView,
            val tvPriceCustomer: TextView,
            val tvPriceSales: TextView,
            val ivDelete: ImageView
        )

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view: View
            var holder: ViewHolder
            val obj = getItem(position)

            if (convertView == null) {
                view = LayoutInflater.from(context).inflate(R.layout.custom_product_layout, parent, false)

                holder = ViewHolder(view.tvName, view.tvPriceCustomer, view.tvPriceSales, view.ivDelete)
                view.setTag(holder)
            } else {
                view = convertView
                holder = view.tag as ViewHolder
            }

            holder.tvName.text = "$position "+obj.name
            holder.tvPriceCustomer.text = obj.price_customer.toString()
            holder.tvPriceSales.text = obj.price_sales.toString()

            holder.ivDelete.setOnClickListener {


                //                msg(context , "Clicked")
                val alert = AlertDialog.Builder(context).create()
                alert.setTitle("Confirmation")
                alert.setTitle("Are you sure ?")

                alert.setButton(AlertDialog.BUTTON_POSITIVE, "Yes") { dialog, which ->
                    object : AsyncTask<String, Int, String>() {

                        override fun onPreExecute() {
                            super.onPreExecute()
                            activity.pbLoading.visibility = View.VISIBLE
                        }

                        @SuppressLint("WrongThread")
                        override fun doInBackground(vararg params: String): String {
                            return send(params[0], params[1], params[2], params[3])
                        }

                        override fun onPostExecute(result: String) {
                            super.onPostExecute(result)
                            activity.pbLoading.visibility = View.GONE

//                            activity.LoadData()
                        }
                    }.execute("${BASE_URL}/v1/products/${obj.id}", "", "DELETE", "")

                    activity.listProduct.remove(obj)
                    activity.productAdapter?.notifyDataSetChanged()
                }

                alert.setButton(AlertDialog.BUTTON_NEGATIVE, "No", DialogInterface.OnClickListener { dialog, which ->

                })

                alert.show()

            }


            return view
        }

        override fun getItem(position: Int): Product {
            return list.get(position)
        }

        override fun getItemId(position: Int): Long {
            return -1;
        }

        override fun getCount(): Int {
            return list.size
        }

    }
}
