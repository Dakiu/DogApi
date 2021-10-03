package com.destinogames.dogapi

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.destinogames.dogapi.databinding.DoglistBinding

class BreedViewHolder(view: View):RecyclerView.ViewHolder(view){

    private val binding = DoglistBinding.bind(view)

    fun bind(name:String){
        binding.dogtextView.text=name
    }
}