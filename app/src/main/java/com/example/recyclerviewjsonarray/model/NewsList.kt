package com.example.recyclerviewjsonarray.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//Data for json bound resources
data class NewsList(val rows :ArrayList<RecyclerData>)
@Parcelize
data class RecyclerData(val title:String,val description:String,val imageHref:String) : Parcelable
