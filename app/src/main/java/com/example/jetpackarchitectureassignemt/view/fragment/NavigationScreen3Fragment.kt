package com.example.jetpackarchitectureassignemt.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.databinding.FragmentNavigationScreen3Binding
import com.example.jetpackarchitectureassignemt.view.activity.NavigationActivity
import kotlinx.android.synthetic.main.fragment_navigation_screen3.*


class NavigationScreen3Fragment : Fragment() {
    private lateinit var bingingNavigationScreen3Fragment: FragmentNavigationScreen3Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bingingNavigationScreen3Fragment=DataBindingUtil.inflate(inflater,R.layout.fragment_navigation_screen3,container,false)
         return bingingNavigationScreen3Fragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actionBar=(activity as NavigationActivity).supportActionBar
        actionBar?.title=requireContext().getString(R.string.screen3)
        btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}