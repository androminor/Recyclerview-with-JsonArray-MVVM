package com.example.recyclerviewjsonarray.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewjsonarray.R
import com.example.recyclerviewjsonarray.model.RecylerData
import com.squareup.picasso.Picasso

class NewsListAdapter: RecyclerView.Adapter<NewsListAdapter.CustomViewHolder>() {

    var items = ArrayList<RecylerData>()

    @SuppressLint("NotifyDataSetChanged")
    fun setLatestData(items:ArrayList<RecylerData>) {
        this.items = items
        notifyDataSetChanged()
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val title:TextView= itemView.findViewById(R.id.title)
        private val desc:TextView= itemView.findViewById(R.id.description)
        private val imageBox: ImageView = itemView.findViewById(R.id.imageref)

        fun bind(data:RecylerData) {
            title.text = data.title
            desc.text = data.description

            val url = data.imageHref

            Picasso.get().load(url).into(imageBox)


        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_list_row,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder:CustomViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}