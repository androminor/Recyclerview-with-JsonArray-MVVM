package com.example.recyclerviewjsonarray.model

data class NewsList(val rows :ArrayList<RecyclerData>)
data class RecyclerData(val title:String,val description:String,val imageHref:String)
