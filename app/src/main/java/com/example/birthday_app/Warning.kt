package com.example.birthday_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_warning.*

class Warning : AppCompatActivity() {

    companion object{
        const val displayname: String = "null"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warning)
        Strt.setOnClickListener{
            val name = intent.getStringExtra(displayname)
            val intent = Intent(this, Display_Activity::class.java)
            intent.putExtra(Display_Activity.dispname, name.toString())
            startActivity(intent)
        }


    }
}