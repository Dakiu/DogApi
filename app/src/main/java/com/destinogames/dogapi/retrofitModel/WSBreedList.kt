package com.destinogames.dogapi.retrofitModel

import com.google.gson.annotations.SerializedName

data class WSBreedList(@SerializedName("message") var breeds:List<String>) {
}