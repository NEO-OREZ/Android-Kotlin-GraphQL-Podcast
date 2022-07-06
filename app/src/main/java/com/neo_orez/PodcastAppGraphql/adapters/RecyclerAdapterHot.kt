package com.neo_orez.PodcastAppGraphql.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neo_orez.PodcastAppGraphql.DataQueryHotQuery
import com.neo_orez.PodcastAppGraphql.databinding.ItemLayoutBinding


class RecyclerAdapterHot (private val homeFeed : ArrayList<DataQueryHotQuery.Data1>) : RecyclerView.Adapter<MyViewHolderHot>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderHot {
        val bindingH = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolderHot(bindingH)
    }

    override fun getItemCount(): Int {
        return homeFeed.size
    }

    override fun onBindViewHolder(holderHot: MyViewHolderHot, position: Int) {
        val feed2 = homeFeed[position]
        holderHot.bindingH.tvTitle.text = feed2.title
        holderHot.bindingH.tvDescription.text = feed2.description
        val thumbnailOne = holderHot.bindingH.ivImage
        Glide.with(holderHot.itemView).load(feed2.imageUrl).into(thumbnailOne)
    }
}

class MyViewHolderHot(val bindingH: ItemLayoutBinding) : RecyclerView.ViewHolder(bindingH.root) {

//    init {
//        view.setOnClickListener {
//            println("test")
//            val intentV = Intent (view.context , MainActivity::class.java)
//            view.context.startActivity(intentV)
//        }
//   }
}