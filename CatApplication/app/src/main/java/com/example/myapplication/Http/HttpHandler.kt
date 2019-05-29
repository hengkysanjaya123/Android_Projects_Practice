package com.example.myapplication.Http

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

fun send(strUrl: String, method: String, params: String, token: String): String {
    val url = URL(strUrl)
    var sb = StringBuilder()

    with(url.openConnection() as HttpURLConnection) {
        requestMethod = method

        setRequestProperty("Content-Type", "application/json")
        setRequestProperty("Accept", "application/json")

        if (!token.isEmpty()) {
            setRequestProperty("x-api-key", "$token")
        }

        if (!params.isEmpty()) {
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