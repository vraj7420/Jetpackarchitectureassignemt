package com.example.jetpackarchitectureassignemt.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackarchitectureassignemt.Util
import com.example.jetpackarchitectureassignemt.network.BaseService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelLiveDataBindingViewModel:ViewModel() {
    val pageNumber= MutableLiveData<String>()
    var apiFailure= MutableLiveData<String>()
    var pageDataList= MutableLiveData<ArrayList<PageModel>>()

    fun getPageData() {
        BaseService().getBaseApi().getData(Util.tagApi, pageNumber.value.toString()).enqueue(object : Callback<PageList?> {
            override fun onResponse(call: Call<PageList?>, response: Response<PageList?>) {
                val pageBody = response.body()
                if (pageBody?.pageList?.isEmpty() == true) {
                    pageDataList.value= pageBody.pageList
                } else {
                    pageDataList.value= pageBody?.pageList
                }
            }
            override fun onFailure(call: Call<PageList?>, t: Throwable) {
                apiFailure.value=t.message
            }
        })
    }

}