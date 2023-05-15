package com.example.mvvmwithhiltwithretrofit.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.mvvmwithhiltwithretrofit.R
import com.example.mvvmwithhiltwithretrofit.databinding.ActivityDetailsBinding
import com.example.mvvmwithhiltwithretrofit.databinding.ActivityMainBinding

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = intent.getIntExtra("position", -1)
        val title = intent.getStringExtra("title")
        val imageURL = intent.getStringExtra("imageURL")
        val id = intent.getStringExtra("id")
        val brand = intent.getStringExtra("brand")
        var message = intent.getDoubleExtra("message",0.0)

        Glide.with(binding.imageView).load(imageURL).into(binding.imageView)

        binding.textView4.text= title
        binding.textView5.text= brand
        binding.textView6.text= message.toString() + " RS"
    }
}