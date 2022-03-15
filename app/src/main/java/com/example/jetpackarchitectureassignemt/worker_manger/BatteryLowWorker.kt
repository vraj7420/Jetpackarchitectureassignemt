package com.example.jetpackarchitectureassignemt.worker_manger

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.Util

class BatteryLowWorker(var ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {
        Util().notificationCreate(
            ctx, R.drawable.ic_battery_low, ctx.getString(R.string.battery_low), ctx.getString(R.string.battery_low_short_description))
        return Result.success()
    }

}