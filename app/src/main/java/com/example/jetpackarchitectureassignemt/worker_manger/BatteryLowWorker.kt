package com.example.jetpackarchitectureassignemt.worker_manger

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.jetpackarchitectureassignemt.background.BatteryLowReceiver

class BatteryLowWorker(var ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    private lateinit var batteryLowReceiver: BatteryLowReceiver
    override fun doWork(): Result {
        batteryLowReceiver = BatteryLowReceiver()
         ctx.registerReceiver(batteryLowReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
         return Result.success()
    }

     override fun onStopped() {
        super.onStopped()
        ctx.unregisterReceiver(batteryLowReceiver)
    }

}