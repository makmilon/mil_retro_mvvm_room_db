package com.example.mvvmwithhiltwithretrofit.favorite_item.favorite_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmwithhiltwithretrofit.R
import com.example.mvvmwithhiltwithretrofit.favorite_item.ProductFav
import com.example.mvvmwithhiltwithretrofit.viewmodel.MyViewModel

class FavoriteAdapter(private val favorites: List<ProductFav>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = favorites[position]
        holder.title.text = favorite.title
        holder.brand.text = favorite.brand
        holder.price.text = favorite.price.toString()
        Glide.with(holder.image).load(favorite.imageURL).into(holder.image)
          }

    override fun getItemCount() = favorites.size

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textView)
        val brand: TextView = itemView.findViewById(R.id.textView2)
        val price: TextView = itemView.findViewById(R.id.textView3)
        val image: ImageView = itemView.findViewById(R.id.imgView)
    }
}
