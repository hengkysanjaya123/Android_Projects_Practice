package com.example.myapplication

import android.content.DialogInterface
import android.os.Bundle
import android.support.constraint.Constraints
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            val dialog = AlertDialog.Builder(this).create()
            dialog.setTitle("Alert")
            dialog.setMessage("Alert dialog message")
            dialog.setIcon(R.drawable.abc_edit_text_material)

            val et = EditText(this)
            et.hint = "Type a message here"

            val layout_param = Constraints.LayoutParams(Constraints.LayoutParams.MATCH_PARENT, Constraints.LayoutParams.WRAP_CONTENT)
            layout_param.leftMargin = 15;
            layout_param.rightMargin = 15;

            et.layoutParams = layout_param
//            et.width = ConstraintLayout.LayoutParams.MATCH_PARENT

            et.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int)
                {
                }

                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int)
                {
                    if (s.length != 0){
                        dialog.getButton(AlertDialog.BUTTON_NEUTRAL).isEnabled = true
                    }
                    else{
                        dialog.getButton(AlertDialog.BUTTON_NEUTRAL).isEnabled = false
                    }
                }
            })

            dialog.setView(et)

            dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK") { dialog, which ->
                if (et.text.isEmpty()) {
                    Toast.makeText(this, "Please fill out the text", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Hello you click the ok button \nText : ${et.text}", Toast.LENGTH_SHORT).show()
                }
            }

            dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this, "Hello you click the positive button", Toast.LENGTH_SHORT).show()
            })

            dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", resources.getDrawable(R.drawable.abc_ic_star_black_16dp), DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this, "Hello you click the cancel button", Toast.LENGTH_SHORT).show()
            })

            dialog.setCancelable(false)

            dialog.show()
            (dialog as AlertDialog).getButton(AlertDialog.BUTTON_NEUTRAL).isEnabled = false



        }
    }
}
