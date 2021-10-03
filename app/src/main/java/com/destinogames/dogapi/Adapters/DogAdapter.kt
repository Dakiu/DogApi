package com.destinogames.dogapi.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.destinogames.dogapi.DogViewHolder
import com.destinogames.dogapi.R

class DogAdapter(private val images:List<String>):RecyclerView.Adapter<DogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.dogimagerow,parent,false))
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val item:String =images[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = images.size
}