package com.example.myapplication.Helper

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.myapplication.Http.send

val TOKEN = "39c19c57-c616-4b0b-93f1-6341a94c632d"
val BASE_URL = "https://api.thecatapi.com"

fun message(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun doAsync(
    strUrl: String,
    method: String,
    params: String,
    token: String,
    pb: ProgressBar? = null,
    callBack: (s: String) -> Unit
) {
    object : AsyncTask<String, Int, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            pb?.visibility = View.VISIBLE
        }

        @SuppressLint("WrongThread")
        override fun doInBackground(vararg params: String): String {
            return send(params[0], params[1], params[2], params[3])
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            pb?.visibility = View.GONE
            callBack(result)
        }
    }.execute(strUrl, method, params, token)
}