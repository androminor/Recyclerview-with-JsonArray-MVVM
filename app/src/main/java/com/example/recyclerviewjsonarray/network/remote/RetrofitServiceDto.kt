package com.example.recyclerviewjsonarray.network.remote

import com.example.recyclerviewjsonarray.model.NewsList
import retrofit2.http.GET

interface RetrofitServiceDto {
    @GET("facts")
    suspend fun getDataFromApi() : NewsList
}