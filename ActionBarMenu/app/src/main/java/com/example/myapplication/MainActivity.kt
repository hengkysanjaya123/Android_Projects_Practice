package com.example.myapplication

import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionBar?.title = "ActionBar"

        supportActionBar?.title = "Action Bar "

        actionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        actionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        actionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)

        actionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.abc_hint_foreground_material_light)))
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.abc_hint_foreground_material_light)))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.activity_action_bar_menu, menu)
        return true;
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId ){
            R.id.menu_toast -> {
                msg(this, "Menu toast clicked")
            }
            R.id.menu_launch ->{
                msg(this, "Menu launch clicked")
            }
            else->{

            }
        }

        return true;
    }
}
