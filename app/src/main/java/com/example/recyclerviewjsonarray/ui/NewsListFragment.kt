package com.example.recyclerviewjsonarray.ui


import android.os.Bundle
import android.util.Log

import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewjsonarray.R
import com.example.recyclerviewjsonarray.databinding.FragmentNewsListBinding
import com.example.recyclerviewjsonarray.model.RecyclerData
import kotlinx.android.synthetic.main.fragment_news_list.*

private const val TAG ="NewsListFragment"
//The view of MVVM architecture
class NewsListFragment : Fragment() {
    /* view binding with late init as dont want to redraw again n again
     also late init promises no nullable data when it is called later */
    private lateinit var binding: FragmentNewsListBinding
    //Kotlin property delegate used to define viewmodels
    val viewmodel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsListAdapter
    var arryList:ArrayList<RecyclerData> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewsListBinding.inflate(layoutInflater)
        Log.i(TAG,"onCreate")

        //Creating the observer which updates the UI(Main Thread)
        if(savedInstanceState==null)
            viewmodel.newsMutableLiveData.observe(this.viewLifecycleOwner, {

                if(it!=null )
                {
                    hideProgressBar()
                    Log.i(TAG,"Received the data")
                    arryList=it.getContentIfNotHandled()!!.rows;
                    newsAdapter.setLatestData(arryList,activity)
                }
                else {
                    showProgressBar()
                    Toast.makeText(activity,"No data",Toast.LENGTH_LONG).show()
                }

            })
        else{
            arryList= savedInstanceState.getParcelableArrayList(TAG)!!;
            hideProgressBar()

        }
        initAdapterModel()
        return binding.root

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(TAG,arryList)
        super.onSaveInstanceState(outState)
    }
    private fun saveState(): Bundle? {
        val state = Bundle()
        state.putParcelableArrayList(TAG,arryList )
        return state
    }
    //Binding the recycler view adapter and with the news adapter
    private fun initAdapterModel() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        newsAdapter = NewsListAdapter()
        newsAdapter.setLatestData(arryList,requireActivity())
        binding.recyclerView.adapter = newsAdapter

    }
    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }



    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.scrolltotop,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        binding.recyclerView.smoothScrollToPosition(0)
        return super.onOptionsItemSelected(item)
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            NewsListFragment()
    }
}