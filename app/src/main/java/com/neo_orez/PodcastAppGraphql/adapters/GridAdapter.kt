package com.neo_orez.PodcastAppGraphql.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.neo_orez.PodcastAppGraphql.GridModel
import com.neo_orez.PodcastAppGraphql.R

class GridAdapter (val grid_List: List<GridModel>, private val context: Context) : BaseAdapter() {

    private var layoutInflater: LayoutInflater? = null
    private lateinit var gridTXT: TextView
    private lateinit var gridIMG: ImageView

    override fun getCount(): Int {
        return grid_List.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView0 = convertView
        if (layoutInflater == null) {
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView0 == null) {
            convertView0 = layoutInflater!!.inflate(R.layout.item_grid_category,null)
        }
        gridTXT = convertView0!!.findViewById(R.id.gridTextID)
        gridIMG = convertView0!!.findViewById(R.id.gridImageID)
        gridIMG.setImageResource(grid_List.get(position).gridImage)
        gridTXT.setText(grid_List.get(position).gridText)

        return convertView0
    }
}
