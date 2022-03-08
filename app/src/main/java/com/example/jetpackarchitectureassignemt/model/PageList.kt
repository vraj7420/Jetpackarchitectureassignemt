package com.example.jetpackarchitectureassignemt.model

import com.google.gson.annotations.SerializedName

class PageList {
    @SerializedName("hits")
    val pageList=ArrayList<PageModel>()

}