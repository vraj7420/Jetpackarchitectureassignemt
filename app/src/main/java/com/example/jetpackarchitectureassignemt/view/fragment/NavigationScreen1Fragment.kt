package com.example .jetpackarchitectureassignemt.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.databinding.FragmentNavigationScreen1Binding
import com.example.jetpackarchitectureassignemt.view.activity.NavigationActivity
import kotlinx.android.synthetic.main.fragment_navigation_screen1.*


class NavigationScreen1Fragment : Fragment() {
    private lateinit var bindingFragmentNavigationScreen1Binding: FragmentNavigationScreen1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentNavigationScreen1Binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_navigation_screen1,
            container,
            false
        )
        return bindingFragmentNavigationScreen1Binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init(){
        val actionBar=(activity as NavigationActivity).supportActionBar
        actionBar?.title=requireContext().getString(R.string.screen1)
        val navController= Navigation.findNavController(view ?: View(requireContext()))
        btnRedirectToScreen2.setOnClickListener {
            if(switchAddToBackStack.isChecked){
                navController.navigate(R.id.action_navigationScreen1Fragment_to_navigationScreen2Fragment)
            }
            else{
                val navOptions: NavOptions = NavOptions.Builder().setPopUpTo(R.id.navigationScreen1Fragment, true).build()
                navController.navigate(R.id.action_navigationScreen1Fragment_to_navigationScreen2Fragment,null,navOptions)
            }
        }

    }
}