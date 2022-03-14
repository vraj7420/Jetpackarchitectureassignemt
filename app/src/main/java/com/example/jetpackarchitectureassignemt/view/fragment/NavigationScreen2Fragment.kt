package com.example.jetpackarchitectureassignemt.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.databinding.FragmentNavigationScreen2Binding
import com.example.jetpackarchitectureassignemt.view.activity.NavigationActivity
import kotlinx.android.synthetic.main.fragment_navigation_screen2.*


class NavigationScreen2Fragment : Fragment() {
    private lateinit var bindingNavigationScreen2Fragment:FragmentNavigationScreen2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingNavigationScreen2Fragment=DataBindingUtil.inflate(inflater, R.layout.fragment_navigation_screen2,container,false)
        return  bindingNavigationScreen2Fragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        }
    private fun init(){
        val actionBar=(activity as NavigationActivity).supportActionBar
        actionBar?.title=requireContext().getString(R.string.screen2)
        val navController= Navigation.findNavController(view ?: View(requireContext()))
        btnRedirectToScreen3.setOnClickListener {
            if(switchAddToBackStack.isChecked){
                navController.navigate(R.id.action_navigationScreen2Fragment_to_navigationScreen3Fragment)

            }else{
                val navOptions: NavOptions = NavOptions.Builder().setPopUpTo(R.id.navigationScreen2Fragment, true).build()
                navController.navigate(R.id.action_navigationScreen2Fragment_to_navigationScreen3Fragment,null,navOptions)
            }
        }
    }
}