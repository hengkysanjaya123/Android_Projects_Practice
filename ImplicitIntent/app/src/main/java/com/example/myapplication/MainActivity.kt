package com.example.myapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupPermissions()

        // text
        btn.setOnClickListener{
            val intt = Intent()
            intt.action = Intent.ACTION_SEND
            intt.putExtra(Intent.EXTRA_TEXT, "Hello world!")
            intt.type = "text/plain"

            startActivity(Intent.createChooser(intt, "Choose app : "))
        }

        // url
        btn2.setOnClickListener{
            val intt = Intent(Intent.ACTION_VIEW)

            intt.setData(Uri.parse("http://www.tutlane.com"))
            startActivity(intt)
        }

        // Calling using intent
        btn3.setOnClickListener{
            val intt = Intent(Intent.ACTION_CALL)
            intt.data = Uri.parse("tel:082129041797")
            startActivity(intt)
        }

        // Call log
        btn4.setOnClickListener{
            val intt = Intent(Intent.ACTION_VIEW)
            intt.type = CallLog.Calls.CONTENT_TYPE
            startActivity(intt)
        }

        btn5.setOnClickListener{
            val intt = Intent(Intent.ACTION_PICK)
            intt.type = ContactsContract.Contacts.CONTENT_TYPE
            startActivity(intt)
        }


    }

    fun setupPermissions(){
        val permission = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.CALL_PHONE)
        if(permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    fun makeRequest(){
        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.CALL_PHONE),
            101
            )
    }
}
