package com.example.jetpackarchitectureassignemt.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.Util
import com.example.jetpackarchitectureassignemt.databinding.ActivityViewModelLiveDataDataBindingBinding
import com.example.jetpackarchitectureassignemt.view.fragment.PageNumberTackFragment
import kotlinx.android.synthetic.main.activity_view_model_live_data_data_binding.*

class ViewModelLiveDataDataBindingActivity : AppCompatActivity() {
    companion object {
        lateinit var bindingActivity: ActivityViewModelLiveDataDataBindingBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivity = DataBindingUtil.setContentView(this, R.layout.activity_view_model_live_data_data_binding)
        init()
        Util().setFragment(fragment_container_view.id,this,PageNumberTackFragment())
    }

     private fun init(){
         val actionBar=supportActionBar
         actionBar?.title =getString(R.string.view_model_livedata_data_binding)
     }
}