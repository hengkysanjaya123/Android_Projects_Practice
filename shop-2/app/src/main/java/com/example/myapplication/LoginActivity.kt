package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.myapplication.Helper.BASE_URL
import com.example.myapplication.Helper.CORE_TOKEN
import com.example.myapplication.Helper.message
import com.example.myapplication.Http.send
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val context: Context = this

        btnLogin.setOnClickListener {
            if (etEmail.text.isEmpty() || etPassword.text.isEmpty()) {
                message(this, "All data must be filled")
                return@setOnClickListener
            }

            val params = "{\"username\" : \"${etEmail.text}\"," +
                    "\"password\" : \"${etPassword.text}\"" +
                    "}"

            object : AsyncTask<String, Int, String>() {

                override fun onPreExecute() {
                    super.onPreExecute()
                    pbLoading.visibility = View.VISIBLE
                }

                @SuppressLint("WrongThread")
                override fun doInBackground(vararg params: String): String {
                    return send(params[0], params[1], params[2], params[3])
                }

                override fun onPostExecute(result: String) {
                    super.onPostExecute(result)
                    pbLoading.visibility = View.GONE

                    if (result.toLowerCase().contains("message")) {
                        val obj = JSONObject(result)
                        var message = obj.getString("message")

                        message(context, message)
                    } else {
                        val obj = JSONObject(result)
                        var data = obj.getJSONObject("data")
                        val signature = data.getString("signature")
                        CORE_TOKEN = signature

                        message(context, CORE_TOKEN)
                        val intt = Intent(context, ProductAdminActivity::class.java)
                        startActivity(intt)
                    }
                }
            }.execute("$BASE_URL/v1/auth", params, "POST", "")
        }


        textView.setOnClickListener {
            val intt = Intent(context, RegisterActivity::class.java)
            startActivity(intt)
        }

    }


}
