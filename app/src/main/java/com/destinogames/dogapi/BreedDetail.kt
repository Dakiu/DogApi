package com.destinogames.dogapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.destinogames.dogapi.Adapters.DogAdapter
import com.destinogames.dogapi.databinding.ActivityBreedDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BreedDetail : AppCompatActivity() {

    private lateinit var binding: ActivityBreedDetailBinding
    private val dogImages = mutableListOf<String>()
    private lateinit var adapter:DogAdapter
    private var breed = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBreedDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        breed = intent.getStringExtra("breed").toString()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = DogAdapter(dogImages)
        binding.dogImageRecyclerView.layoutManager= LinearLayoutManager(this)
        binding.dogImageRecyclerView.adapter = adapter
        getImageList()

    }

    private fun getImageList(){
        CoroutineScope(Dispatchers.IO).launch {
            val call=getRetrofit().create(APIService::class.java).getDogsByBreeds("breed/"+breed+"/images")
            val response = call.body()

            runOnUiThread {
                if (call.isSuccessful) {
                    val breeds = response?.images ?: emptyList()
                    dogImages.addAll(breeds)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}