package com.example.jetpackarchitectureassignemt.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}