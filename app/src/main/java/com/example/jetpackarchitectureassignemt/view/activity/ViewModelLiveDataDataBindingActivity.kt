package com.example.jetpackarchitectureassignemt.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.databinding.ActivityViewModelLiveDataDataBindingBinding

class ViewModelLiveDataDataBindingActivity : AppCompatActivity() {
    companion object {
        lateinit var bindingActivity: ActivityViewModelLiveDataDataBindingBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivity = DataBindingUtil.setContentView(this, R.layout.activity_view_model_live_data_data_binding)
    }

    override fun onStart() {
        super.onStart()
        init()
    }

     private fun init(){
         val actionBar=supportActionBar
         actionBar?.title =getString(R.string.view_model_livedata_data_binding)
     }
}