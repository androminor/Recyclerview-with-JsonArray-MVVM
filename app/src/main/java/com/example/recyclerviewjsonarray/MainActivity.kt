package com.example.recyclerviewjsonarray

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewjsonarray.network.remote.RetrofitInstanceDto
import com.example.recyclerviewjsonarray.network.remote.RetrofitServiceDto
import com.example.recyclerviewjsonarray.ui.NewsListAdapter
import com.example.recyclerviewjsonarray.ui.NewsListFragment
import com.example.recyclerviewjsonarray.ui.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFragment()
        supportActionBar?.title = "NewsReaderAussie"

        refreshButton.setOnClickListener {
          setupFragment()
        }
    }

    private fun setupFragment() {
        val fragment = NewsListFragment.newInstance()
        val fragmentManager:FragmentManager = supportFragmentManager
        val fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.commit()
    }


}