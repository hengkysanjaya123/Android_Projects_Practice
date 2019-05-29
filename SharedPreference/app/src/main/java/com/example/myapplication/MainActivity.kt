package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = this.getSharedPreferences("testing", Context.MODE_PRIVATE)



        btnClickHere.setOnClickListener {
            val editor = sharedPref.edit()
            editor.putString("text", "abcabc")
            editor.apply()
            //            setSharedPreference(this, "data", "hai.. testing")
        }

        btnGet.setOnClickListener {
            //            val result = getSharedPreference(this, "data")
            val result = sharedPref.getString("text", "")
            tvResult.setText(result)
        }
    }

    fun setSharedPreference(mContext: Context, name: String, value: String) {
        val sp = PreferenceManager.getDefaultSharedPreferences(mContext).edit()
        sp.putString(name, value)
        sp.apply()
    }

    fun getSharedPreference(mContext: Context, name: String): String? {
        val sp = PreferenceManager.getDefaultSharedPreferences(mContext)
        return sp.getString(name, null)
    }
}
