package com.example.jetpackarchitectureassignemt.worker_manger

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.jetpackarchitectureassignemt.background.DeviceIdleStateReceiver

class DeviceIdleStateWorker(var ctx:Context,params:WorkerParameters):Worker(ctx,params) {
    private lateinit var deviceIdleStateService: DeviceIdleStateReceiver

    override fun doWork(): Result {
        deviceIdleStateService=DeviceIdleStateReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF)
        intentFilter.addAction(Intent.ACTION_SCREEN_ON)
        ctx.registerReceiver(deviceIdleStateService, intentFilter)
          return Result.success()
     }


    override fun onStopped() {
        super.onStopped()
        ctx.unregisterReceiver(deviceIdleStateService)
    }
}