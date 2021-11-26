package com.example.recyclerviewjsonarray.ui

import androidx.lifecycle.*
import com.example.recyclerviewjsonarray.model.NewsList
import com.example.recyclerviewjsonarray.network.remote.RetrofitInstanceDto
import com.example.recyclerviewjsonarray.network.remote.RetrofitServiceDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//viewmodel for handling clean archietecture
class NewsViewModel(): ViewModel() {
    //Mutable live data for the news list
    var newsMutableLiveData: MutableLiveData<NewsList> = MutableLiveData()

    //viewmodel will observe the latest updated data with the help of mutable live data
    fun newsListObserver():MutableLiveData<NewsList> {
        return newsMutableLiveData
    }

/*  making an api call using viewmodel scope (custom coroutines scope can be used as well)
    launch is like a builder . Here it is launching Dispatcher.IO for memory intensive operation
    Now inside we will create synchronized retrofit instance and fetch the response
    in the form of getDataFromApi() with a delay of 2 seconds respectively
    post value is called lastly for setting the value from a background thread */
    fun makeApiCall () {
        viewModelScope.launch(Dispatchers.IO) {
            val retrofitInstance = RetrofitInstanceDto.getRetrofitInstance().create(RetrofitServiceDto::class.java)
            val response = retrofitInstance.getDataFromApi()
            delay(2000)
            newsMutableLiveData.postValue(response)
        }
    }


}