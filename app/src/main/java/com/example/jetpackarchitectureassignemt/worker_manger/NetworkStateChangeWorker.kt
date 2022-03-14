package com.example.jetpackarchitectureassignemt.worker_manger

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.jetpackarchitectureassignemt.background.NetworkStateReceiver

class NetworkStateChangeWorker(var ctx:Context,parameters: WorkerParameters):Worker(ctx,parameters) {
    private var networkStateChangeReceiver: NetworkStateReceiver = NetworkStateReceiver()

    override fun doWork(): Result {
        ctx.registerReceiver(networkStateChangeReceiver,IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
     return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        ctx.unregisterReceiver(networkStateChangeReceiver)
    }
}