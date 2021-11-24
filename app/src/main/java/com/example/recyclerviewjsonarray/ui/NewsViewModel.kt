package com.example.recyclerviewjsonarray.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewjsonarray.model.NewsList
import com.example.recyclerviewjsonarray.network.remote.RetrofitInstanceDto
import com.example.recyclerviewjsonarray.network.remote.RetrofitServiceDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel:ViewModel() {
    var newsMutableLiveData: MutableLiveData<NewsList> = MutableLiveData()

    fun newsListObserver():MutableLiveData<NewsList> {
        return newsMutableLiveData
    }
    fun makeApiCall () {
        viewModelScope.launch(Dispatchers.IO) {
            val retrofitInstance = RetrofitInstanceDto.getRetrofitInstance().create(RetrofitServiceDto::class.java)
            val response = retrofitInstance.getDataFromApi()
            newsMutableLiveData.postValue(response)
        }
    }

}