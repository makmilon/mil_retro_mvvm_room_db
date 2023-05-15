package com.example.mvvmwithhiltwithretrofit.pages

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmwithhiltwithretrofit.Repository.Repository
import com.example.mvvmwithhiltwithretrofit.databinding.ActivityFavoriteBinding
import com.example.mvvmwithhiltwithretrofit.favorite_item.favorite_adapter.FavoriteAdapter
import com.example.mvvmwithhiltwithretrofit.viewmodel.MyViewModel
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {



    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var viewModel: MyViewModel
    private lateinit var binding: ActivityFavoriteBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}
