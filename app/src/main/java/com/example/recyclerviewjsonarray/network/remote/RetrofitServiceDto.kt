package com.example.recyclerviewjsonarray.network.remote

import com.example.recyclerviewjsonarray.model.NewsList
import retrofit2.http.GET

//interface for communication with the server through GET requests
interface RetrofitServiceDto {
    @GET("facts")

   /* suspend fun to let the compiler know that coroutine is being used
    & the below function can be paused or resumed in an inferred fashion */
    suspend fun getDataFromApi() : NewsList
}