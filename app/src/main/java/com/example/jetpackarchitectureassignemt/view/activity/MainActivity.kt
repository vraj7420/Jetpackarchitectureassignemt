package com.example.jetpackarchitectureassignemt.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var bindingActivity: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivity=DataBindingUtil.setContentView(this,R.layout.activity_main)
        setListener()
    }

    private fun setListener() {
        btnViewModel.setOnClickListener {
            val intentNextScreen=Intent(this,ViewModelLiveDataDataBindingActivity::class.java)
            startActivity(intentNextScreen)
        }
        btnRoom.setOnClickListener {
            val intentRoomScreen=Intent(this,RoomActivity::class.java)
            startActivity(intentRoomScreen)
        }
        btnWorkManger.setOnClickListener {
            val intentWorkManger=Intent(this,WorkMangerActivity::class.java)
            startActivity(intentWorkManger)
        }
        btnNavigationComponent.setOnClickListener {
            val intentNavigationActivity=Intent(this,NavigationActivity::class.java)
            startActivity(intentNavigationActivity)
        }
    }
}