package com.example.myapplication.Http

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun send(urlStr : String, params : String, method: String, token: String): String {

    val url = URL(urlStr)
    var sb = StringBuilder()

    with(url.openConnection() as HttpURLConnection) {
        requestMethod = method

        setRequestProperty("Accept", "application/json")
        setRequestProperty("Content-Type", "application/json")

        if (!token.isEmpty()) {
            setRequestProperty("Authorization", "Bearer $token")
        }

        if(!params.isEmpty()){
            val wr = DataOutputStream(outputStream)
            wr.writeBytes(params)
        }

        var reader: BufferedReader? = null

        val responseCode = getResponseCode()
        if (responseCode >= HttpURLConnection.HTTP_BAD_REQUEST) {
            reader = BufferedReader(InputStreamReader(errorStream))
        } else {
            reader = BufferedReader(InputStreamReader(inputStream))
        }

        reader.forEachLine {
            sb.append(it)
        }

        reader.close()
    }
    return sb.toString()
}