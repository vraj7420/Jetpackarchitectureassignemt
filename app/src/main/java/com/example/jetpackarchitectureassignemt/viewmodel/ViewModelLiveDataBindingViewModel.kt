package com.example.jetpackarchitectureassignemt.viewmodel

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
    var apiFailure = MutableLiveData<String>()
    private lateinit var pageInfoAdapter: PageInfoAdapter
    var bindingPageNumberTack: FragmentPageNumberTackBinding? = null
    var bindingPageDataShow: FragmentPageDataShowBinding? = null
    var pageDataList = MutableLiveData<ArrayList<PageModel>>()

    fun getPageData() {
        bindingPageDataShow?.pbWaiting?.visibility=View.VISIBLE
        BaseService().getBaseApi().getData(Util.tagApi, pageNumber.value.toString())
            .enqueue(object : Callback<PageList?> {
                override fun onResponse(call: Call<PageList?>, response: Response<PageList?>) {
                    val pageBody = response.body()
                    if (pageBody?.pageList?.isEmpty() == true) {
                        pageDataList.value = pageBody.pageList
                    } else {
                        pageDataList.value = pageBody?.pageList
                        setAdapter()
                    }
                }
                override fun onFailure(call: Call<PageList?>, t: Throwable) {
                    apiFailure.value = t.message
                    bindingPageDataShow?.rvPageData?.visibility=View.GONE
                    bindingPageDataShow?.pbWaiting?.visibility=View.GONE
                    bindingPageDataShow?.tvError?.visibility=View.VISIBLE
                }
            })
    }

    @SuppressLint("ResourceAsColor")
    fun setBackGroundBtn() {
        if (pageNumber.value?.isNotEmpty() == true) {
            bindingPageNumberTack?.btnSubmit?.setBackgroundResource(R.color.purple_500)
            bindingPageNumberTack?.btnSubmit?.isClickable = true
            bindingPageNumberTack?.btnSubmit?.setTextColor(R.color.white)
        } else {
            bindingPageNumberTack?.btnSubmit?.setBackgroundResource(R.color.gray_C1BFBF)
            bindingPageNumberTack?.btnSubmit?.isClickable = false
            bindingPageNumberTack?.btnSubmit?.setTextColor(R.color.black)
        }
    }

    /**
     * Returns the Lifecycle of the provider.
     *
     * @return The lifecycle of the provider.
     */
    override fun getLifecycle(): Lifecycle {
        TODO("Not yet implemented")
    }
    private fun setAdapter() {
        pageInfoAdapter = PageInfoAdapter(pageDataList.value)
        bindingPageDataShow?.rvPageData?.adapter = pageInfoAdapter
        bindingPageDataShow?.pbWaiting?.visibility =View.GONE
    }
}