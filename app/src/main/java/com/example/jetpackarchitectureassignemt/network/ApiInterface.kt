package com.example.jetpackarchitectureassignemt.network

import com.example.jetpackarchitectureassignemt.Util
import com.example.jetpackarchitectureassignemt.model.PageList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET(Util.apiSubUrl)
    fun getData(@Query(Util.apiQueryTagArg)tag:String,@Query(Util.apiQueryPageArg)pageNumber:String):Call<PageList>
}