package com.example.recyclerviewjsonarray.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_ETHERNET
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.NetworkCapabilities.*
import android.os.Build
import android.provider.ContactsContract.CommonDataKinds.Email.TYPE_MOBILE
import androidx.lifecycle.*
import com.example.recyclerviewjsonarray.NewsApplication
import com.example.recyclerviewjsonarray.model.NewsList
import com.example.recyclerviewjsonarray.network.remote.RetrofitInstanceDto
import com.example.recyclerviewjsonarray.network.remote.RetrofitServiceDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsViewModel(app:Application): AndroidViewModel(app) {
    var newsMutableLiveData: MutableLiveData<NewsList> = MutableLiveData()

    fun newsListObserver():MutableLiveData<NewsList> {
        return newsMutableLiveData
    }
    fun makeApiCall () {
        viewModelScope.launch(Dispatchers.IO) {
            val retrofitInstance = RetrofitInstanceDto.getRetrofitInstance().create(RetrofitServiceDto::class.java)
            val response = retrofitInstance.getDataFromApi()
            delay(2000)
            newsMutableLiveData.postValue(response)
        }
    }

    private fun hasNetworkConnection():Boolean {
        val connectivityMan = getApplication<NewsApplication>()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNet = connectivityMan.activeNetwork ?: return false
            val capabilities = connectivityMan.getNetworkCapabilities(activeNet) ?: return false
            return when{
                capabilities.hasTransport(TRANSPORT_WIFI)->true
                capabilities.hasTransport(TRANSPORT_CELLULAR)->true
                capabilities.hasTransport(TRANSPORT_ETHERNET)->true
                else -> false
            }
        }
        else {
            connectivityMan.activeNetworkInfo?.run {
                return when(type)
                {
                    TYPE_WIFI -> true
                    TYPE_MOBILE->true
                    TYPE_ETHERNET->true
                    else -> false
                }
            }
        }
        return false
    }

}