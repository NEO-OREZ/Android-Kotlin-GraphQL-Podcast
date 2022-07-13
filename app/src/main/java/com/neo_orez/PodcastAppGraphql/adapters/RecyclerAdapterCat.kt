package com.neo_orez.PodcastAppGraphql.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neo_orez.PodcastAppGraphql.DataCategoriesQuery
import com.neo_orez.PodcastAppGraphql.databinding.ItemCategoriesLayoutBinding

class RecyclerAdapterCat(
    private val homeFeed : ArrayList<DataCategoriesQuery.Data1>) : RecyclerView.Adapter<MyViewHolderHot>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderHot {
        val bindingC = ItemCategoriesLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolderHot(bindingC)
    }

    override fun getItemCount(): Int {
        return homeFeed.size
    }

    override fun onBindViewHolder(holderHot: MyViewHolderHot, position: Int) {
        val feed2 = homeFeed[position]
        holderHot.bindingC.tvTitle.text = feed2.title
        holderHot.bindingC.tvDescription.text = feed2.description
        val thumbnailOne = holderHot.bindingC.ivImage
        Glide.with(holderHot.itemView).load(feed2.imageUrl).into(thumbnailOne)
    }
}

class MyViewHolderHot(val bindingC: ItemCategoriesLayoutBinding) : RecyclerView.ViewHolder(bindingC.root) {
}