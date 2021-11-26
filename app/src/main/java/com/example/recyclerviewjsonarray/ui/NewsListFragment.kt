package com.example.recyclerviewjsonarray.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.recyclerviewjsonarray.R
import com.example.recyclerviewjsonarray.databinding.FragmentNewsListBinding
import kotlinx.android.synthetic.main.fragment_news_list.*


class NewsListFragment : Fragment() {

    private lateinit var binding: FragmentNewsListBinding
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewsListBinding.inflate(layoutInflater)
        initAdapterModel()
        initViewModel()
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.scrolltotop,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        binding.recyclerView.smoothScrollToPosition(0)

        return super.onOptionsItemSelected(item)
    }

    private fun initAdapterModel() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        newsAdapter = NewsListAdapter()
        binding.recyclerView.adapter = newsAdapter
    }

    private fun initViewModel() {

        viewModel.newsListObserver().observe(viewLifecycleOwner, {

            if(it!=null )
            {   hideProgressBar()
                newsAdapter.setLatestData(it.rows,activity)
            }
            else {
                showProgressBar()
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