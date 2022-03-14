package com.example.jetpackarchitectureassignemt.worker_manger

import android.content.Context
import android.content.IntentFilter
import android.location.LocationManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.jetpackarchitectureassignemt.background.GpsEnableDisableReceiver

class GpsEnableDisableWorker(var ctx:Context,params: WorkerParameters) :Worker(ctx,params) {
    private lateinit var gpsEnableDisableReceiver: GpsEnableDisableReceiver
    override fun doWork(): Result {
        gpsEnableDisableReceiver = GpsEnableDisableReceiver()
        ctx.registerReceiver(
            gpsEnableDisableReceiver,
            IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        )
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        ctx.unregisterReceiver(gpsEnableDisableReceiver)
    }
}