package com.example.recyclerviewjsonarray.network.remote

import com.example.recyclerviewjsonarray.utility.ObjectUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceDto {
    //A builder pattern for Retrofit instance inside the companion object as a static access
    companion object {
        fun getRetrofitInstance():Retrofit {
            return Retrofit.Builder()
                .baseUrl(ObjectUtils.retroBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}