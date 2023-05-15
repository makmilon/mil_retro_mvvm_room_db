package com.example.mvvmwithhiltwithretrofit.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmwithhiltwithretrofit.R
import com.example.mvvmwithhiltwithretrofit.databinding.ItemLayoutBinding
import com.example.mvvmwithhiltwithretrofit.favorite_item.ProductFav
import com.example.mvvmwithhiltwithretrofit.netWork.dataClass.Product
import com.example.mvvmwithhiltwithretrofit.pages.DetailsActivity
import com.example.mvvmwithhiltwithretrofit.viewmodel.MyViewModel


class RecyclerViewAdapter(private val viewModel: MyViewModel): RecyclerView.Adapter<RecyclerViewAdapter.MyRecViewHolder>() {

    private var listData : List<Product>?=null

    fun setListData(listData : List<Product>){
        this.listData= listData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyRecViewHolder(view, viewModel)
    }

    override fun onBindViewHolder(holder: MyRecViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return listData?.size ?: 0
    }

    class MyRecViewHolder(view: View, private val viewModel: MyViewModel): RecyclerView.ViewHolder(view){

        var message: Double?=null
        val heart= view.findViewById<ImageView>(R.id.imageView3)

        fun bind(data: Product){

            // Set values for the views using data binding
            val binding = DataBindingUtil.bind<ItemLayoutBinding>(itemView)
            binding?.product = data


            val priceList = data.price
            if (priceList.isNotEmpty()) {
                message = priceList[0].value
               // Use the price value as needed
            }
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val context = itemView.context
                    val intent = Intent(context, DetailsActivity::class.java)
                    intent.putExtra("position", position)
                    intent.putExtra("title", data.title)
                    intent.putExtra("imageURL", data.imageURL)
                    intent.putExtra("id", data.id)
                    intent.putExtra("brand", data.brand)
                    intent.putExtra("message", message)

                    context.startActivity(intent)
                }
            }

            val dataF = object {
                var isFavorite = true // or false
            }

            // Add an OnClickListener to the favorite button
            heart.setOnClickListener {
                // Update the product's isFavorite property
                dataF.isFavorite = !dataF.isFavorite

                // Update the favorite button image
                if (dataF.isFavorite) {
                    heart.setImageResource(R.drawable.heart_red)

                    // Save data to local database
                    val productFav = ProductFav(
                        id = data.id,
                        title = data.title,
                        brand = data.brand,
                        imageURL = data.imageURL,
                        isFavorite = dataF.isFavorite,
                        price = message ?: 0.0
                    )
                    viewModel.insertRecord(productFav)
                } else {
                    heart.setImageResource(R.drawable.heart_black)

                    // Delete data from local database
                    val productFav = ProductFav(
                        id = data.id,
                        title = data.title,
                        brand = data.brand,
                        imageURL = data.imageURL,
                        isFavorite = dataF.isFavorite,
                        price = message ?: 0.0
                    )
                    viewModel.deleteRecord(productFav)
                }
            }
        }
    }
}

