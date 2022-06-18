package com.federicocotogno.JsonRetroSpotify.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.federicocotogno.JsonRetroSpotify.MainActivity
import com.federicocotogno.JsonRetroSpotify.R
import kotlinx.android.synthetic.main.item_layout.view.*
import om.federicocotogno.JsonRetroSpotify.DataQuery

class RecyclerAdapter (val homefeed : List<DataQuery.Data1>): RecyclerView.Adapter<myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val layoutinflater_var = LayoutInflater.from(parent.context)
        val cell_row = layoutinflater_var.inflate(R.layout.item_layout ,parent ,false )
        return myViewHolder(cell_row)
    }

    override fun getItemCount(): Int {
        val XYX = homefeed.size
        return XYX
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val feedVar = homefeed[position]
        holder.itemView.tv_title.text = feedVar.title
        holder.itemView.tv_description.text = feedVar.description
        //holder.itemView.tv_url.text = feedVar.url.toString()

       val thumbnail_one = holder.itemView.iv_image
       Glide.with(holder.itemView).load(feedVar.imageUrl).into(thumbnail_one)
    }
}

class myViewHolder(view:View) : RecyclerView.ViewHolder(view) {
    init {
        view.setOnClickListener {
            println("test")
            val intentV = Intent (view.context , MainActivity::class.java)
            view.context.startActivity(intentV)
        }
    }
}