package com.example.jetpackarchitectureassignemt.worker_manger

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.Util

class NetworkStateChangeWorker(var ctx:Context,parameters: WorkerParameters):Worker(ctx,parameters) {

    override fun doWork(): Result {
        Util().notificationCreate(
            ctx,
            R.drawable.ic_wifi,
            ctx.getString(R.string.network_state),
            ctx.getString(R.string.network_change_short_description)
        )
        return Result.success()
    }


}