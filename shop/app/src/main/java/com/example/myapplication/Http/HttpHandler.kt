package com.example.myapplication.Http

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Base64
import android.util.Log
import com.example.myapplication.Helper.Core
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class HttpHandler {
    companion object {
        fun sendGet(context: Context, strUrl: String): String {
            val url = URL(strUrl)
            var sb: StringBuilder = StringBuilder();


            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "GET"

                println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")

                inputStream.bufferedReader().use {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        it.lines().forEach {
                            println(it)
                            sb.appendln(it)
                        }
                    } else {
                        throw Exception("Sorry current SDK version not supported to use in inputstream")
                    }
                }
            }

            return sb.toString();
        }

        fun sendGetWithAuth(context: Context, strUrl: String, token: String): String {
            val url = URL(strUrl)
            var sb: StringBuilder = StringBuilder();

            try {
//                val basicAuth = String(Base64.encode(token.toByteArray(), Base64.DEFAULT))
                with(url.openConnection() as HttpURLConnection) {
                    try {
                        requestMethod = "GET"

                        setRequestProperty("Authorization", "Bearer $token")

                        inputStream.bufferedReader().use {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                it.lines().forEach {
                                    sb.append(it)
                                }
                            } else {
                                throw Exception("Sorry current SDK version not supported to use in inputstream")
                            }
                        }
                    } catch (ex: Exception) {
                        throw Exception(ex.toString())
                    }
                }
            } catch (ex: Exception) {
                throw Exception(ex.toString())
            }

            return sb.toString()
        }

        fun sendPost(strurl: String, context: Context, params: String): String {
            val url = URL(strurl)
            val sb  = StringBuilder()

            with(url.openConnection() as HttpURLConnection){
                requestMethod = "POST"

                setRequestProperty("Accept", "application/json")
                setRequestProperty("Content-Type", "application/json")

                val wr = DataOutputStream(outputStream)
                wr.writeBytes(params)

                val status = responseCode
                var reader : BufferedReader? = null

                if(status > HttpURLConnection.HTTP_BAD_REQUEST){
                    reader = BufferedReader(InputStreamReader(errorStream))
                }
                else{
                    reader = BufferedReader(InputStreamReader(inputStream))
                }

                reader.forEachLine {
                    sb.append(it)
                }

                reader.close();
            }


            return sb.toString()
        }

        fun sendPostWithAuth(context: Context, strUrl: String, params: ByteArray, token: String): String {
            val url = URL(strUrl)
            var sb: StringBuilder = StringBuilder()

            try {
//                val basicAuth = String(Base64.encode(token.toByteArray(), Base64.DEFAULT))
                with(url.openConnection() as HttpURLConnection) {

                    requestMethod = "POST"

                    if (token != "") {
                        setRequestProperty("Authorization", "Bearer $token")
                    }

                    setRequestProperty("Accept", "application/json")
                    setRequestProperty("Content-Type", "application/json")

                    val wr = DataOutputStream(outputStream)
                    wr.write(params)

                    inputStream.bufferedReader().use {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            it.lines().forEach {
                                sb.append(it)
                            }
                        } else {
                            throw Exception("Sorry current SDK version not supported to use in inputstream")
                        }
                    }
                }
            } catch (ex: Exception) {
                throw Exception(ex.toString())
            }

            return sb.toString()
        }
    }
}