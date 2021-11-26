package com.example.recyclerviewjsonarray.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewjsonarray.R
import com.example.recyclerviewjsonarray.databinding.NewsListRowBinding
import com.example.recyclerviewjsonarray.model.RecyclerData

class NewsListAdapter() : RecyclerView.Adapter<NewsListAdapter.CustomViewHolder>() {

    var items = ArrayList<RecyclerData>()
    lateinit var activity: Context

    @SuppressLint("NotifyDataSetChanged")
    fun setLatestData(items: ArrayList<RecyclerData>, activity: FragmentActivity?) {
        this.items = items
        if (activity != null) {
            this.activity = activity
        }
        notifyDataSetChanged()
    }

    class CustomViewHolder(binding: NewsListRowBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.title
        val desc: TextView = binding.description
        val imageBox: ImageView = binding.imageref


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CustomViewHolder {

        val binding =
          NewsListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val itemsViewModel = items[position]

        if (itemsViewModel.imageHref !=null) {
                holder.title.text = itemsViewModel.title
                holder.desc.text = itemsViewModel.description

            var imgUrl = itemsViewModel.imageHref.replace("http", "https")
                Glide.with(activity)
                    .load(imgUrl)
                    .into(holder.imageBox);
            }


    }

    override fun getItemCount(): Int {
        return items.size
    }

}