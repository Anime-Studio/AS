package com.example.anime_studio_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter(
    private val titleList: List<String>,
    private val epCountList: List<String>,
    private val posterList: List<String>
) : RecyclerView.Adapter<Adapter.YourViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.anime_list, parent, false)
        return YourViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: YourViewHolder, position: Int) {
        val item1 = titleList[position]
        val item2 = epCountList[position]
        val item3 = posterList[position]

        // Bind data to views in your ViewHolder
        holder.textView1.text = item1
        holder.textView2.text = item2
        Glide.with(holder.itemView)
            .load(item3[position])
            .centerCrop()
            .into(holder.image)
    }

    override fun getItemCount() = minOf(titleList.size, epCountList.size, posterList.size)
    class YourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.title)
        val textView2: TextView = itemView.findViewById(R.id.epcount)
        val image: ImageView = itemView.findViewById(R.id.poster)
    }
}