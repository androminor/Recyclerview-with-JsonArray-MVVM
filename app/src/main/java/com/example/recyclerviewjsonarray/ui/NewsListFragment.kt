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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.recyclerviewjsonarray.R
import kotlinx.android.synthetic.main.fragment_news_list.*


class NewsListFragment : Fragment() {


    private  lateinit var viewModel :NewsViewModel
    private lateinit var newsAdapter: NewsListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_news_list, container,false)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        initViewModel()
        initViewModel(view)



        // Inflate the layout for this fragment
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

        viewModel.newsListObserver().observe(viewLifecycleOwner, {

            if(it!=null )
            {   hideProgressBar()
                newsAdapter.setLatestData(it.rows,activity)
            }
            else {
                Toast.makeText(activity,"No data",Toast.LENGTH_LONG).show()
            }
        })
        viewModel.makeApiCall()
    }
    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            NewsListFragment()
    }
}