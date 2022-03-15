package com.example.jetpackarchitectureassignemt.worker_manger

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class GpsEnableDisableWorker(var ctx:Context,params: WorkerParameters) :Worker(ctx,params) {
    override fun doWork(): Result {
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
    }
}