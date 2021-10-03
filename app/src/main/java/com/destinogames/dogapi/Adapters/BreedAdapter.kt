package com.destinogames.dogapi.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.destinogames.dogapi.R
import com.destinogames.dogapi.BreedDetail
import com.destinogames.dogapi.BreedViewHolder

class BreedAdapter (private val c:Context, private val breed:List<String>): RecyclerView.Adapter<BreedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return BreedViewHolder(layoutInflater.inflate(R.layout.doglist, parent, false))
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val item: String = breed[position]
        holder.bind(item)
        holder.itemView.setOnClickListener(){
            Toast.makeText(c,breed[position],Toast.LENGTH_LONG).show();

            val intent = Intent(c, BreedDetail::class.java)
            intent.putExtra("breed",breed[position])
            c.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = breed.size

}