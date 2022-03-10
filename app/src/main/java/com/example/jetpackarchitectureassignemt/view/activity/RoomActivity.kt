package com.example.jetpackarchitectureassignemt.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.Util
import com.example.jetpackarchitectureassignemt.databinding.ActivityRoomBinding
import com.example.jetpackarchitectureassignemt.view.fragment.StudentListFragment
import kotlinx.android.synthetic.main.activity_room.*

class RoomActivity : AppCompatActivity() {
    companion object {
        lateinit var bindingRoomActivity:ActivityRoomBinding
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingRoomActivity= DataBindingUtil.setContentView(this, R.layout.activity_room)
        init()
        Util().setFragment(fragment_container_view_room.id,this, StudentListFragment())
    }
    private fun init(){
        val actionBar=supportActionBar
        actionBar?.title =getString(R.string.room)
    }
}