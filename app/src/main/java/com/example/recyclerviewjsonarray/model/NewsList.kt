package com.example.recyclerviewjsonarray.model

data class NewsList(val rows :ArrayList<RecylerData>)
data class RecylerData(val title:String,val description:String,val imageHref:String)
