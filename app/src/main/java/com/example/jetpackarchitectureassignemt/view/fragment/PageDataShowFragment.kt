package com.example.jetpackarchitectureassignemt.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.Util
import com.example.jetpackarchitectureassignemt.adapter.PageInfoAdapter
import com.example.jetpackarchitectureassignemt.databinding.FragmentPageDataShowBinding
import com.example.jetpackarchitectureassignemt.model.ViewModelLiveDataBindingViewModel

class PageDataShowFragment : Fragment() {
    private lateinit var bindingPageDataShowFragment: FragmentPageDataShowBinding
    private lateinit var pageDataShowViewModel: ViewModelLiveDataBindingViewModel
    private lateinit var pageInfoAdapter: PageInfoAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingPageDataShowFragment =
            DataBindingUtil.inflate(inflater, R.layout.fragment_page_data_show, container, false)
        return bindingPageDataShowFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setObserver()
    }

    private fun setObserver() {
        pageDataShowViewModel.pageDataList.observe(viewLifecycleOwner, {
            bindingPageDataShowFragment.pbWaiting.visibility=View.VISIBLE
            setAdapter()
        })
        pageDataShowViewModel.apiFailure.observe(viewLifecycleOwner, {
            bindingPageDataShowFragment.tvError.text = pageDataShowViewModel.apiFailure.value
            bindingPageDataShowFragment.rvPageData.visibility = View.GONE
            bindingPageDataShowFragment.tvError.visibility = View.VISIBLE

        })
    }

    private fun setAdapter() {
        pageInfoAdapter = PageInfoAdapter(pageDataShowViewModel.pageDataList.value)
        bindingPageDataShowFragment.rvPageData.layoutManager = LinearLayoutManager(requireContext())
        bindingPageDataShowFragment.rvPageData.adapter = pageInfoAdapter
        bindingPageDataShowFragment.pbWaiting.visibility=View.GONE
    }

    private fun init() {
        pageDataShowViewModel = ViewModelProvider(requireActivity()).get(ViewModelLiveDataBindingViewModel::class.java)
        bindingPageDataShowFragment.pageDataShow = pageDataShowViewModel
        bindingPageDataShowFragment.lifecycleOwner = activity
        if(Util().checkForInternet(requireContext())){
        pageDataShowViewModel.getPageData()
        }else{
            bindingPageDataShowFragment.tvError.text = requireContext().getString(R.string.no_Internet)
            bindingPageDataShowFragment.rvPageData.visibility = View.GONE
            bindingPageDataShowFragment.tvError.visibility = View.VISIBLE
        }
    }
}