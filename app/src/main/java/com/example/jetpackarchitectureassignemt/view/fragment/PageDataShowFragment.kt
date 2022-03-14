package com.example.jetpackarchitectureassignemt.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.adapter.PageInfoAdapter
import com.example.jetpackarchitectureassignemt.databinding.FragmentPageDataShowBinding
import com.example.jetpackarchitectureassignemt.viewmodel.ViewModelLiveDataBindingViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_page_data_show.*

class PageDataShowFragment : Fragment() {
    private lateinit var pageInfoAdapter: PageInfoAdapter
    private lateinit var bindingPageDataShowFragment: FragmentPageDataShowBinding
    private lateinit var pageDataShowViewModel: ViewModelLiveDataBindingViewModel

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
        viewModelSetUp()
    }

    private fun viewModelSetUp() {
        pageDataShowViewModel =
            ViewModelProvider(requireActivity()).get(ViewModelLiveDataBindingViewModel::class.java)
        bindingPageDataShowFragment.pageDataShow = pageDataShowViewModel
        bindingPageDataShowFragment.lifecycleOwner = activity
        pageDataShowViewModel.bindingPageDataShow = bindingPageDataShowFragment
        pageDataShowViewModel.getPageData(requireContext())
        pageDataShowViewModel.pageDataList.observe(viewLifecycleOwner, {
            setAdapter()
        })
    }

    private fun setAdapter() {
        pageInfoAdapter = PageInfoAdapter(pageDataShowViewModel.pageDataList.value) { itemPosition, item ->
           Snackbar.make(rootPageDataShow,"$itemPosition , ${item.title}",Snackbar.LENGTH_SHORT).show()
        }
        rvPageData?.adapter = pageInfoAdapter

    }
}