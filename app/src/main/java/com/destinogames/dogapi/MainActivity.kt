package com.destinogames.dogapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.destinogames.dogapi.Adapters.BreedAdapter
import com.destinogames.dogapi.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter:BreedAdapter
    private val dogList = mutableListOf<String>()
    private val tempArrayList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

        binding.buscarsearchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText!!.lowercase()

                tempArrayList.clear()
                if (searchText.isNotEmpty()){
                    dogList.forEach{
                        if (it.lowercase().contains(searchText)){
                            tempArrayList.add(it)
                        }
                    }
                }else{
                    tempArrayList.clear()
                    tempArrayList.addAll(dogList)
                }
                adapter.notifyDataSetChanged()

                return false
            }

        })

    }

    private fun initRecyclerView() {
        tempArrayList.addAll(dogList)
        adapter = BreedAdapter(this,tempArrayList)
        binding.dogBreedRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.dogBreedRecyclerView.adapter = adapter
        getBreedList()
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getBreedList(){
        CoroutineScope(Dispatchers.IO).launch {
            val call=getRetrofit().create(APIService::class.java).getBreedList("breeds/list")
            val response = call.body()

            runOnUiThread {

                if (call.isSuccessful) {
                    val breeds = response?.breeds ?: emptyList()
                    tempArrayList.addAll(breeds)
                    dogList.addAll(breeds)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
    }

}