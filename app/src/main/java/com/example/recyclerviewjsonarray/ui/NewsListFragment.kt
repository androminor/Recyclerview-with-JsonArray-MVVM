package com.example.recyclerviewjsonarray.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewjsonarray.R

class NewsListFragment : Fragment() {

    private lateinit var newsAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_news_list, container,false)
        initViewModel(view)
        initViewModel()
        return view
    }

    private fun initViewModel(view: View?) {
    val recyclerView =view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        val decorationItem = DividerItemDecoration(activity,DividerItemDecoration.HORIZONTAL)
        recyclerView?.addItemDecoration(decorationItem)
        newsAdapter = NewsListAdapter()
        recyclerView?.adapter = newsAdapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModel.newsListObserver().observe(viewLifecycleOwner, {
            if(it!=null)
            {
                newsAdapter.setLatestData(it.rows,activity)
            }
            else {
                Toast.makeText(activity,"No data",Toast.LENGTH_LONG).show()
            }
        })
        viewModel.makeApiCall()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            NewsListFragment()
    }
}