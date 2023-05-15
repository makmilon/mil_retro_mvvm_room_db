package com.example.mvvmwithhiltwithretrofit

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmwithhiltwithretrofit.adapter.RecyclerViewAdapter
import com.example.mvvmwithhiltwithretrofit.databinding.ActivityMainBinding
import com.example.mvvmwithhiltwithretrofit.pages.FavoriteActivity
import com.example.mvvmwithhiltwithretrofit.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    private lateinit var viewModel: MyViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        setSupportActionBar(binding.toolbar)

        inItRecView()
        initViewModel()
    }


    private fun inItRecView(){

        binding.recView.layoutManager = GridLayoutManager(this, 3) // 2 is the number of columns
        recyclerViewAdapter = RecyclerViewAdapter(viewModel)
        binding.recView.adapter = recyclerViewAdapter

    }

    @SuppressLint("SuspiciousIndentation")
    private fun initViewModel(){
      val viewModel=  ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            if (it !=null){
                recyclerViewAdapter.setListData(it)
                recyclerViewAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(applicationContext, "error getting data", Toast.LENGTH_SHORT).show()
            }

        })
        viewModel.loadDataFromApi()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.my_icon -> {
                // Handle menu item click here
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
                return true
            }
            // Add more cases for other menu items as needed
            else -> return super.onOptionsItemSelected(item)
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.my_icon -> {
//                Toast.makeText(applicationContext, "This is clicked", Toast.LENGTH_SHORT).show()
////                val intent = Intent(this, FavoriteActivity::class.java)
////                startActivity(intent)
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }


}