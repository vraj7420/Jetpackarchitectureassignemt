package com.example.jetpackarchitectureassignemt.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackarchitectureassignemt.ConstantString
import com.example.jetpackarchitectureassignemt.R
import kotlinx.android.synthetic.main.activity_notification_description.*

class NotificationDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_description)
        tvAlarmTitle.text = intent.getStringExtra(ConstantString.alarmTitle)
        tvSetAlarmShortDescription.text = intent.getStringExtra(ConstantString.alarmShortDescription)
        tvSetAlarmLongDescription.text = intent.getStringExtra(ConstantString.alarmLongDescription)

    }
}