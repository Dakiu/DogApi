package com.destinogames.dogapi

import com.destinogames.dogapi.retrofitModel.WSBreedList
import com.destinogames.dogapi.retrofitModel.WSResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getDogsByBreeds(@Url url:String):Response<WSResponse>
    @GET
    suspend fun getBreedList(@Url url:String): Response<WSBreedList>
}