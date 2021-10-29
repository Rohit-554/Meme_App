package com.example.birthday_app

import MySingleton
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_display.*

class Display_Activity : AppCompatActivity() {
    var currenturl:String?=null
    companion object{
        const val dispname : String = "null"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        val name = intent.getStringExtra(dispname)
        val upr_cse = name?.uppercase()
        display_name.text="HI! ${upr_cse} \n Don't Laugh"
        loadmeme()

        Nextmeme.setOnClickListener {
            loadmeme()
        }
        sharememe.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type="text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,"Laughing isn't Good Get memes $currenturl")
            val chooser = Intent.createChooser(intent,"Share to ..")
            startActivity(chooser)
        }


    }


    fun loadmeme()
    {
        loading.visibility=View.VISIBLE
        val url = "https://meme-api.herokuapp.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                currenturl = response.getString("url")
                Glide.with(this).load(currenturl).listener(object:RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        loading.visibility=View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        loading.visibility=View.GONE
                        return false
                    }

                }).into(memedisplay)
            },
            { error ->
                Toast.makeText(this, "Something Went Wrong! Try Checking Internet Connection", Toast.LENGTH_SHORT).show()
            })

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }


}
