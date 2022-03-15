package com.example.jetpackarchitectureassignemt.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.worker_manger.BatteryLowWorker
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_notify_battery_low.*
import java.util.concurrent.TimeUnit


class NotifyBatteryLowFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notify_battery_low, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
      }


  private  fun init(){
        val constraints: Constraints = Constraints.Builder().setRequiresBatteryNotLow(true).build()
        val workManager = WorkManager.getInstance(requireContext())
        val sendingLog = PeriodicWorkRequest.Builder(BatteryLowWorker::class.java,15,TimeUnit.MINUTES).setConstraints(constraints).build()
        btnSetAlarm?.setOnClickListener {
            workManager.enqueue(sendingLog)
            Snackbar.make(requireActivity().findViewById(android.R.id.content),requireContext().getString(R.string.set_alarm), Snackbar.LENGTH_LONG).show()
        }
        btnStopAlarm?.setOnClickListener {
            WorkManager.getInstance(requireContext()).cancelAllWork()
            Snackbar.make(requireActivity().findViewById(android.R.id.content),requireContext().getString(R.string.stop_alarm), Snackbar.LENGTH_LONG).show()
        }
  }
}


