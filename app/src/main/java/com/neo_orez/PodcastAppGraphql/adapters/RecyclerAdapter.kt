package com.neo_orez.PodcastAppGraphql.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neo_orez.PodcastAppGraphql.DataQuery
import com.neo_orez.PodcastAppGraphql.databinding.ItemLayoutBinding

class RecyclerAdapter (private val homeFeed : List<DataQuery.Data1>): RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return homeFeed.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val feed2 = homeFeed[position]
        holder.binding.tvTitle.text = feed2.title
        holder.binding.tvDescription.text = feed2.description
        val thumbnailOne = holder.binding.ivImage
        Glide.with(holder.itemView).load(feed2.imageUrl).into(thumbnailOne)
    }
}

class MyViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

//    init {
//        view.setOnClickListener {
//            println("test")
//            val intentV = Intent (view.context , MainActivity::class.java)
//            view.context.startActivity(intentV)
//        }
//   }
}