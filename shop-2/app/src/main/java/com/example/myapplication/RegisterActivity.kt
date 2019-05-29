package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Patterns
import android.widget.Toast
import com.example.myapplication.Helper.BASE_URL
import com.example.myapplication.Helper.CORE_TOKEN
import com.example.myapplication.Helper.message
import com.example.myapplication.Http.send
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //            message(this, CORE_TOKEN)

        val mcontext = this

        button.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()
            val email = etEmailRegis.text.toString()
            val phone = etPhone.text.toString()

            if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty() || phone.isEmpty()){
                message(this, "All data must be filled")
                return@setOnClickListener
            }

            if(rbMale.isChecked == false && rbFemale.isChecked == false){
                message(this, "Please choose the gender")
                return@setOnClickListener
            }

            if(password != confirmPassword){
//                message(this, "Confirm password and password must be the same")
                etConfirmPassword.error = "Confirm password and password must be the same"
                return@setOnClickListener
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmailRegis.error = "Email format is incorrect"
                return@setOnClickListener
            }

            var gender = ""
            if(rbMale.isChecked){
                gender = "male"
            }
            else{
                gender = "female"
            }

            val obj = JSONObject()
            obj.put("username", username)
            obj.put("password", password)
            obj.put("name", "name")
            obj.put("email", email)
            obj.put("phone", phone)
            obj.put("gender", gender)
//
//            val params = "{\"username\" : \"$username\"," +
//                    "\"password\":\"\"" +
//                    "}"

            object : AsyncTask<String, Int, String>() {

                override fun onPreExecute() {
                    super.onPreExecute()
                    button.isEnabled  = false
                }

                @SuppressLint("WrongThread")
                override fun doInBackground(vararg params: String): String {
                    return send(params[0], params[1], params[2], params[3])
                }

                override fun onPostExecute(result: String) {
                    super.onPostExecute(result)
                    button.isEnabled  = true

                    if(result.contains("message")){
                        val obj = JSONObject(result)
                        val msg = obj.getString("message")
                        message(this@RegisterActivity, msg)
                    }
                    else{
                        message(this@RegisterActivity, "Data inserted successfully")
                        finish()
                    }
                }
            }.execute("$BASE_URL/v1/users", obj.toString(), "POST", "")
        }
    }
}
