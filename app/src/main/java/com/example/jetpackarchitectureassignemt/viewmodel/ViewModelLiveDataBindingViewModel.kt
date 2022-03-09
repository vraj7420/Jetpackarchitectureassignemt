package com.example.jetpackarchitectureassignemt.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.Util
import com.example.jetpackarchitectureassignemt.adapter.PageInfoAdapter
import com.example.jetpackarchitectureassignemt.databinding.FragmentPageDataShowBinding
import com.example.jetpackarchitectureassignemt.databinding.FragmentPageNumberTackBinding
import com.example.jetpackarchitectureassignemt.model.PageList
import com.example.jetpackarchitectureassignemt.model.PageModel
import com.example.jetpackarchitectureassignemt.network.BaseService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelLiveDataBindingViewModel : ViewModel(), LifecycleOwner {
    val pageNumber = MutableLiveData<String>()
    var apiFailure = MutableLiveData("")
    private lateinit var pageInfoAdapter: PageInfoAdapter
    var bindingPageNumberTack: FragmentPageNumberTackBinding? = null
    var bindingPageDataShow: FragmentPageDataShowBinding? = null
    var pageDataList = MutableLiveData<ArrayList<PageModel>>()
    val pageNumberLiveData: LiveData<String>
        get() = pageNumber

    fun getPageData(ctx:Context) {
        apiFailure.value=""
        pageDataList.value = null
        if(!Util().checkForInternet(ctx)){
            apiFailure.value=ctx.getString(R.string.no_Internet)
            return
        }
        BaseService().getBaseApi().getData(Util.tagApi, pageNumber.value.toString())
            .enqueue(object : Callback<PageList?> {
                override fun onResponse(call: Call<PageList?>, response: Response<PageList?>) {
                    val pageBody = response.body()
                    if (pageBody?.pageList?.isEmpty() == true) {
                        pageDataList.value = pageBody.pageList
                        apiFailure.value=ctx.getString(R.string.no_data)
                    } else {
                        pageDataList.value = pageBody?.pageList
                    }
                }
                override fun onFailure(call: Call<PageList?>, t: Throwable) {
                    apiFailure.value = t.message
                }
            })
    }
    /**
     * Returns the Lifecycle of the provider.
     *
     * @return The lifecycle of the provider.
     */
    override fun getLifecycle(): Lifecycle {
        TODO("Not yet implemented")
    }
}