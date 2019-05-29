package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.JsonReader
import android.view.View
import com.example.myapplication.Helper.Core
import com.example.myapplication.Http.HttpHandler
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import org.json.JSONStringer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.setOnClickListener {
            val intt = Intent(this, RegisterActivity::class.java)
            startActivity(intt)
        }

        button.setOnClickListener {
            if (etEmail.text.isEmpty() || etPassword.text.isEmpty()) {
                Core.Message(this, "All data must be filled")
                return@setOnClickListener
            }


            val bodyText = "{\n" +
                    "\t\"username\":\"${etEmail.text}\",\n" +
                    "\t\"password\":\"${etPassword.text}\"\n" +
                    "}"

            val postData = bodyText.toByteArray()
            pbloading.visibility = View.VISIBLE


            Thread {
                try {
//                    val text = HttpHandler.sendPostWithAuth(this, "http://10.0.2.2:8000/v1/auth", postData, "")
                    val text = HttpHandler.sendPost("http://10.0.2.2:8000/v1/auth", this, bodyText)

                    runOnUiThread {
                        if (text.contains("message")) {
                            val obj = JSONObject(text)
                            val message = obj.getString("message")
                            Core.Message(this, message)
                        } else {
                            val obj = JSONObject(text)
                            val data = obj.getJSONObject("data")
                            val signature = data.getString("signature")
                            Core.Message(this, signature)
                        }

                        pbloading.visibility = View.GONE
//
//                        val obj = JSONObject(text).getJSONObject("data")
//                        val signature = obj.getString("signature")
//                        Core.core_token = signature
//
//                        val intt = Intent(this, RegisterActivity::class.java)
//                        startActivity(intt)
                    }
                } catch (ex: Exception) {
                    runOnUiThread {
                        Core.Message(this, ex.message.toString())
                        pbloading.visibility = View.GONE
                    }
                }
            }.start();


//            val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0OTM2MjU4Ny03NmY0LTQzNDUtOTZjYi0wNDBjNjlmYzk1NGIiLCJ1c2VybmFtZSI6InZhbGVudGlub2VrYXB1dHJhIiwiZXhwIjoxNTU0NDU2MDA2fQ.NxHnw-QSxQUWBWxTz4C-nht1YqouHpuLtdXASxxmcXxH5XGVdhCP0xbPPpBkeLVkdONWj8OhD7wdDOed7wzwDw"


//            Thread{
//                try{
//
//                    val text = HttpHandler.sendGetWithAuth(this, "http://10.0.2.2:8000/v1/users/me", token)
//
//                    runOnUiThread{
//                        Core.Message(this, text)
//                    }
//                }catch (ex : Exception){
//                    runOnUiThread{
//                        Core.Message(this, ex.toString())
//                    }
//                }
//            }.start()


//            Thread {
//                try {
//                    val text = HttpHandler.sendGet(this, "https://jsonplaceholder.typicode.com/posts/42")
//
//                    runOnUiThread {
//                        Core.Message(this, text)
//                    }
//                }
//                catch (ex: Exception) {
//                    runOnUiThread {
//                        Core.Message(this, ex.toString())
//                    }
//                }
//            }.start()

        }
    }
}
