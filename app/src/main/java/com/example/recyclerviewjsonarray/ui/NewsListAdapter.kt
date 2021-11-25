package com.example.recyclerviewjsonarray.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewjsonarray.R
import com.example.recyclerviewjsonarray.model.RecylerData
import kotlinx.android.synthetic.main.news_list_row.view.*

class NewsListAdapter() : RecyclerView.Adapter<NewsListAdapter.CustomViewHolder>() {

    var items = ArrayList<RecylerData>()
    lateinit var activity: Context

    @SuppressLint("NotifyDataSetChanged")
    fun setLatestData(items: ArrayList<RecylerData>, activity: FragmentActivity?) {
        this.items = items
        if (activity != null) {
            this.activity = activity
        }
        notifyDataSetChanged()
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val desc: TextView = itemView.findViewById(R.id.description)
        val imageBox: ImageView = itemView.findViewById(R.id.imageref)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.news_list_row, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val itemsViewModel = items[position]

        if (itemsViewModel.imageHref!=null && itemsViewModel.imageHref.isNotEmpty() ) {

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