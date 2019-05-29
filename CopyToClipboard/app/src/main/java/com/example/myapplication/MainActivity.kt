package com.example.myapplication

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var clipboardManager: ClipboardManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        btnCopy.setOnClickListener {
            val clip = ClipData.newPlainText("text", et.text)
            clipboardManager.primaryClip = clip

            Toast.makeText(this, "Text Copied", Toast.LENGTH_SHORT).show()
        }

        btnPaste.setOnClickListener {
            val clip = clipboardManager.primaryClip
            val item = clip.getItemAt(0)

            tvResult.text = item.text
            Toast.makeText(this, "Text Pasted", Toast.LENGTH_SHORT).show()
        }
    }
}
