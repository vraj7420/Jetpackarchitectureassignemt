package com.example.jetpackarchitectureassignemt.worker_manger

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.Util

class AfterSomeTimeAlarm(var ctx: Context, parameters: WorkerParameters): Worker(ctx,parameters)  {
    override fun doWork(): Result {
        Util().notificationCreate(ctx,
            R.drawable.ic_alarm,ctx.getString(R.string.after_certain_amount_time),ctx.getString(R.string.set_alarm_after_some_time_short_description))
        return Result.success()
    }
}