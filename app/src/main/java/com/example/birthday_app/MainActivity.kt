package com.example.birthday_app

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Display
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nxtactivtybtn.setOnClickListener {
            if (input_text.text.toString().isEmpty()) {
                Toast.makeText(this, "Hey!Please Enter Your Name", Toast.LENGTH_SHORT).show()
            } else {
                val name = input_text.text.toString()
                val intent = Intent(this, Warning::class.java)
                intent.putExtra(Warning.displayname, name)
                startActivity(intent)
            }
        }
    }


}