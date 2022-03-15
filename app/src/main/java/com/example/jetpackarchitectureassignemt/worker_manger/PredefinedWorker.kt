package com.example.jetpackarchitectureassignemt.worker_manger

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.Util

class PredefinedWorker(var ctx: Context, parameters: WorkerParameters): Worker(ctx,parameters) {
     override fun doWork(): Result {
         Util().notificationCreate(ctx,R.drawable.ic_alarm,ctx.getString(R.string.predefined_time_alarm),ctx.getString(R.string.predefined_time_alarm_short_description))
         return Result.success()
    }
}