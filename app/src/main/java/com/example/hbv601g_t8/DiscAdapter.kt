package com.example.hbv601g_t8

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DiscAdapter(private val discList: ArrayList<Disc>):
    RecyclerView.Adapter<DiscAdapter.DiscViewHolder>() {

        private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        context = parent.context
        return DiscViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return discList.size
    }

    override fun onBindViewHolder(holder: DiscViewHolder, position: Int) {
        val currentItem = discList[position]
        holder.title.text = currentItem.name
        holder.price.text = currentItem.price.toString()

        holder.itemView.setOnClickListener {
            val id: Int = discList[position].discid
            val intent = Intent(context, ViewDiscActivity::class.java)
            intent.putExtra("discid", id)
            context.startActivity(intent)
        }

    }

    class DiscViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.iv_image)
        val title : TextView = itemView.findViewById(R.id.tv_title)
        val price : TextView = itemView.findViewById(R.id.tv_price)
    }
}