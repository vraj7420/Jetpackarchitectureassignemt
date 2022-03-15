package com.example.jetpackarchitectureassignemt.worker_manger

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.Util

class DeviceIdleWorker(var ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
     override fun doWork(): Result {
         Util(). notificationCreate(
             ctx,
             R.drawable.ic_device_idle_state,
             ctx.getString(R.string.device_idle),
             ctx.getString(R.string.device_idle_alarm_short_description))

         return Result.success()
    }
}