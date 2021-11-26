package com.example.recyclerviewjsonarray

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.recyclerviewjsonarray.ui.NewsListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Changing the Action bar title
        supportActionBar?.title = "NewsreadyAussie"
        //Setting up NewsListFragment
        setupFragment()
        /*
        checking network connection if present move to the NewsListFragment
        other wise alert dialog box for checking internet connection */

        if (hasNetworkConnection()) {
          setupFragment()
        } else {
             AlertDialog.Builder(this).setTitle("No Internet Connection")
                .setMessage("Please check your internet connection and try again")
                .setPositiveButton(android.R.string.ok) { dialogInterface, Int ->
                    dialogInterface?.dismiss()
                    finish()
                }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }

        refreshButton.setOnClickListener {
            //again calling the NewsListFragment for refreshing resources
          setupFragment()
        }
    }
    //Network connection criteria
    @SuppressLint("ObsoleteSdkInt")
    private fun hasNetworkConnection():Boolean {
        //calling connectivity service
        val connectivityMan = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        //checking version greater then marshmallow check modes of connection otherwise false
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNet = connectivityMan.activeNetwork ?: return false
            val capabilities = connectivityMan.getNetworkCapabilities(activeNet) ?: return false
            return when{
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
                else -> false
            }
        }
        else {
            connectivityMan.activeNetworkInfo?.run {
                return when(type)
                {
                    ConnectivityManager.TYPE_WIFI -> true
                    ContactsContract.CommonDataKinds.Email.TYPE_MOBILE ->true
                    ConnectivityManager.TYPE_ETHERNET ->true
                    else -> false
                }
            }
        }
        return false
    }

    //Replacing the original fragment with NewsListFragment
    private fun setupFragment() {
        val fragment = NewsListFragment.newInstance()
        val fragmentManager:FragmentManager = supportFragmentManager
        val fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.commit()
    }


}