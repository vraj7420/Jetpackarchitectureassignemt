package com.example.jetpackarchitectureassignemt.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.view.activity.ViewModelLiveDataDataBindingActivity
import com.example.jetpackarchitectureassignemt.databinding.FragmentPageNumberTackBinding
import com.example.jetpackarchitectureassignemt.model.ViewModelLiveDataBindingViewModel

class PageNumberTackFragment : Fragment() {
    private lateinit var bindingPageNumberTackFragment: FragmentPageNumberTackBinding
    private lateinit var pageNumberViewModel: ViewModelLiveDataBindingViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindingPageNumberTackFragment = DataBindingUtil.inflate(inflater,
            R.layout.fragment_page_number_tack, container, false)
        return bindingPageNumberTackFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setListener()
    }

    private fun init() {
        pageNumberViewModel = ViewModelProvider(requireActivity()).get(ViewModelLiveDataBindingViewModel::class.java)
        bindingPageNumberTackFragment.pageNumberTackViewModel = pageNumberViewModel
        bindingPageNumberTackFragment.lifecycleOwner = activity
        pageNumberViewModel.pageNumber.value=null
    }

    private fun setListener() {
        pageNumberViewModel.pageNumber.observe(viewLifecycleOwner, {
            if(pageNumberViewModel.pageNumber.value?.isNotEmpty() == true){
                bindingPageNumberTackFragment.btnSubmit.isClickable =true
                bindingPageNumberTackFragment.btnSubmit.setBackgroundResource(R.color.purple_500)
            }
            else{
                bindingPageNumberTackFragment.btnSubmit.isClickable =false
                bindingPageNumberTackFragment.btnSubmit.setBackgroundResource(android.R.color.darker_gray)
            }
        })
        bindingPageNumberTackFragment.btnSubmit.setOnClickListener {
            val setFragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = setFragmentManager.beginTransaction()
            fragmentTransaction.replace(ViewModelLiveDataDataBindingActivity.bindingActivity.fragmentContainerView.id, PageDataShowFragment())
            fragmentTransaction.commit()
        }
    }
}