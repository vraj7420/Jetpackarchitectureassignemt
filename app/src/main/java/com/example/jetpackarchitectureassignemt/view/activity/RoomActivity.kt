package com.example.jetpackarchitectureassignemt.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.databinding.ActivityRoomBinding

class RoomActivity : AppCompatActivity() {
    companion object {
        lateinit var bindingRoomActivity:ActivityRoomBinding
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingRoomActivity= DataBindingUtil.setContentView(this, R.layout.activity_room)
    }

    override fun onStart() {
        super.onStart()
        init()
    }


    private fun init(){
        val actionBar=supportActionBar
        actionBar?.title =getString(R.string.room)
    }
}