package com.example.recyclerviewjsonarray.network.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceDto {

    companion object {
        private const val retroBaseUrl = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"

        fun getRetrofitInstance():Retrofit {
            return Retrofit.Builder()
                .baseUrl(retroBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}