package com.neo_orez.PodcastAppGraphql.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neo_orez.PodcastAppGraphql.DataQuery
import com.neo_orez.PodcastAppGraphql.databinding.ItemLayoutBinding

class RecyclerAdapter (val homefeed : List<DataQuery.Data1>): RecyclerView.Adapter<myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        //val layoutinflater_var = LayoutInflater.from(parent.context)
       // val cell_row = layoutinflater_var.inflate(R.layout.item_layout ,parent ,false )
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return myViewHolder(binding)
        //return myViewHolder(cell_row)
    }

    override fun getItemCount(): Int {
        val XYX = homefeed.size
        return XYX
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        val feedVar = homefeed[position]
        holder.binding.tvTitle.text = feedVar.title
        holder.binding.tvDescription.text = feedVar.description
        //holder.itemView.tv_url.text = feedVar.url.toString()
        val thumbnail_one = holder.binding.ivImage
        Glide.with(holder.itemView).load(feedVar.imageUrl).into(thumbnail_one)
    }
}

class myViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

//    init {
//        view.setOnClickListener {
//            println("test")
//            val intentV = Intent (view.context , MainActivity::class.java)
//            view.context.startActivity(intentV)
//        }
//   }
}