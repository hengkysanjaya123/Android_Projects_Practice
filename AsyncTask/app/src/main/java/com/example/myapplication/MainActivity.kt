package com.example.myapplication

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myVariable = ""

        btn.setOnClickListener {
            object : AsyncTask<String, Int, String>() {

                override fun onPreExecute() {
                    super.onPreExecute()
                    pbLoading.visibility = View.VISIBLE
                }

                @SuppressLint("WrongThread")
                override fun doInBackground(vararg params: String): String {
                    val url = URL(params[0])

                    var sb = StringBuilder()

                    with(url.openConnection() as HttpURLConnection) {

                        requestMethod = "POST"

                        setRequestProperty("Content-Type", "application/json")
                        setRequestProperty("Accept", "application/json")

                        val wr = DataOutputStream(outputStream)
                        wr.writeBytes(params[1])

                        val responseCode = responseCode
                        var reader: BufferedReader? = null

                        if(responseCode >= HttpURLConnection.HTTP_BAD_REQUEST){
                            reader = BufferedReader(InputStreamReader(errorStream))
                        }
                        else{
                            reader = BufferedReader(InputStreamReader(inputStream))
                        }

                        reader.forEachLine {
                            sb.append(it)
                        }
                    }

                    return sb.toString()
                }

                override fun onPostExecute(result: String) {
                    super.onPostExecute(result)

                    OnCompleted(result)
                    pbLoading.visibility = View.GONE
                }
            }.execute("http://10.22.212.116/v1/auth", "{ \"username\" : \"test\",\"password\" : \"abc\" }")


        }
    }

    fun OnCompleted(result: String) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }

}
