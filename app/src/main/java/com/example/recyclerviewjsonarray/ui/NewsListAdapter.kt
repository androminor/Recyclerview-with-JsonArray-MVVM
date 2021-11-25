package com.example.recyclerviewjsonarray.ui

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewjsonarray.R
import com.example.recyclerviewjsonarray.model.RecyclerData


class NewsListAdapter(context: Context?) : RecyclerView.Adapter<NewsListAdapter.CustomViewHolder>() {

    private val mContext = context
    private val TAG = "MyActivity"
    var items = ArrayList<RecyclerData>()

    @SuppressLint("NotifyDataSetChanged")
    fun setLatestData(items:ArrayList<RecyclerData>) {
        this.items = items
        notifyDataSetChanged()
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title:TextView= itemView.findViewById(R.id.title)
        val desc:TextView= itemView.findViewById(R.id.description)
        val imageBox: ImageView = itemView.findViewById(R.id.imageref)

        fun bind(data: RecyclerData) {
            title.text = data.title
            desc.text = data.description

            val url = data.imageHref

                Log.e(TAG,"url : $url")
         //        Picasso.get().load(url).into(imageBox)

            if(url !=null) {
                Glide.with(itemView.getContext())
                    .load(url)
                    .into(imageBox)
            }


            };


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