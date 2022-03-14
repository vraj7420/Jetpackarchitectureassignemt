package com.example.jetpackarchitectureassignemt.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.worker_manger.ChargingModeWorker
import kotlinx.android.synthetic.main.fragment_notify_charge_mode.*
import java.util.concurrent.TimeUnit


class NotifyChargeModeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notify_charge_mode, container, false)
    }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val workManager = WorkManager.getInstance(requireContext())
       val sendingLog = PeriodicWorkRequest.Builder(ChargingModeWorker::class.java,1000, TimeUnit.MILLISECONDS).build()
       btnSetAlarm.setOnClickListener {
           workManager.enqueueUniquePeriodicWork("Battery Charging Mode",
               ExistingPeriodicWorkPolicy.REPLACE,sendingLog)
           Toast.makeText(activity, activity?.getString(R.string.set_alarm), Toast.LENGTH_SHORT)
                .show()

        }
        btnStopAlarm.setOnClickListener {
            WorkManager.getInstance(requireContext()).cancelAllWorkByTag("Battery Charging Mode")
            Toast.makeText(activity, activity?.getString(R.string.stop_alarm), Toast.LENGTH_SHORT)
                .show()
        }

    }
}