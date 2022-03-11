package com.example.jetpackarchitectureassignemt.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.Util
import com.example.jetpackarchitectureassignemt.databinding.ActivityWorkerMangerBinding
import com.example.jetpackarchitectureassignemt.view.fragment.AlarmListFragment
import kotlinx.android.synthetic.main.activity_worker_manger.*

class WorkMangerActivity : AppCompatActivity() {

    companion object {
        lateinit var bindingWorkMangerActivity:ActivityWorkerMangerBinding
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingWorkMangerActivity = DataBindingUtil.setContentView(this, R.layout.activity_worker_manger)
        init()
        Util().setFragment(fragment_container_view_work_manger.id,this,AlarmListFragment())
    }
    private fun init(){
        val actionBar=supportActionBar
        actionBar?.title =getString(R.string.work_manage)
    }
}