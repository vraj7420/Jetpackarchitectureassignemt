package com.example.jetpackarchitectureassignemt.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.Util
import com.example.jetpackarchitectureassignemt.databinding.FragmentPageNumberTackBinding
import com.example.jetpackarchitectureassignemt.viewmodel.ViewModelLiveDataBindingViewModel
import kotlinx.android.synthetic.main.activity_view_model_live_data_data_binding.*
import kotlinx.android.synthetic.main.fragment_page_number_tack.*

class PageNumberTackFragment : Fragment() {
    private lateinit var bindingPageNumberTackFragment: FragmentPageNumberTackBinding
    private lateinit var pageNumberViewModel: ViewModelLiveDataBindingViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindingPageNumberTackFragment = DataBindingUtil.inflate(inflater, R.layout.fragment_page_number_tack, container, false)
        return bindingPageNumberTackFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelSetUp()
        setListener()
    }





    private fun viewModelSetUp() {
        pageNumberViewModel = ViewModelProvider(requireActivity()).get(ViewModelLiveDataBindingViewModel::class.java)
        bindingPageNumberTackFragment.pageNumberTackViewModel = pageNumberViewModel
        bindingPageNumberTackFragment.lifecycleOwner = activity
        pageNumberViewModel.bindingPageNumberTack=bindingPageNumberTackFragment
    }

    private fun setListener() {
         btnSubmit.setOnClickListener {
             Util().setFragment(requireActivity().fragment_container_view.id,requireContext(),PageDataShowFragment())
         }
    }
}