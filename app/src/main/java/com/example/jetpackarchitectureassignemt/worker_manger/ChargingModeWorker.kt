package com.example.jetpackarchitectureassignemt.worker_manger

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.jetpackarchitectureassignemt.background.ChargingModeReceiver

class ChargingModeWorker(var ctx:Context,params: WorkerParameters):Worker(ctx,params) {
    private lateinit var chargingModeReceiver: ChargingModeReceiver
    override fun doWork(): Result {
        chargingModeReceiver = ChargingModeReceiver()
        ctx.registerReceiver(chargingModeReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        ctx.unregisterReceiver(chargingModeReceiver)
    }
}