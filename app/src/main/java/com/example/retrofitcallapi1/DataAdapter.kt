package com.example.retrofitcallapi1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class DataAdapter(val ds: List<DataItem>):RecyclerView.Adapter<DataAdapter.dataview>() {
    inner class dataview(itemview: View):RecyclerView.ViewHolder(itemview){

        val image = itemview.findViewById<ImageView>(R.id.image)
        val id = itemview.findViewById<TextView>(R.id.id)
        val title = itemview.findViewById<TextView>(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dataview {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_item,parent,false)
        return dataview(view)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onBindViewHolder(holder: dataview, position: Int) {
        val data = ds[position]
        holder.id.text = data.id.toString()
        holder.title.text = data.title
        Picasso.get().load(data.thumbnailUrl).into(holder.image)
    }
}