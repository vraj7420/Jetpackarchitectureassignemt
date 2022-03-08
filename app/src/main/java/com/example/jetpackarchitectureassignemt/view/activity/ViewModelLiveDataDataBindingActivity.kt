package com.example.jetpackarchitectureassignemt.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpackarchitectureassignemt.view.fragment.PageNumberTackFragment
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.databinding.ActivityViewModelLiveDataDataBindingBinding

class ViewModelLiveDataDataBindingActivity : AppCompatActivity() {
    companion object {
        lateinit var bindingActivity: ActivityViewModelLiveDataDataBindingBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivity = DataBindingUtil.setContentView(
            this,
            R.layout.activity_view_model_live_data_data_binding
        )
        setFragment()
    }

    private fun setFragment() {
        val setFragmentManager = supportFragmentManager
        val fragmentTransaction = setFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            bindingActivity.fragmentContainerView.id,
            PageNumberTackFragment()
        )
        fragmentTransaction.commit()
    }
}