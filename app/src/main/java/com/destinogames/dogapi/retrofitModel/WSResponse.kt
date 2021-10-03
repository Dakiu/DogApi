package com.destinogames.dogapi.retrofitModel

import com.google.gson.annotations.SerializedName

data class WSResponse(@SerializedName("status")var status:String, @SerializedName("message") var images:List<String>) {
}