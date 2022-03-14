package com.example.jetpackarchitectureassignemt.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.worker_manger.DeviceIdleStateWorker
import kotlinx.android.synthetic.main.fragment_notify_device_idle.*

class NotifyDeviceIdleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notify_device_idle, container, false)

    }

 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     val workManager = WorkManager.getInstance(requireContext())
     val sendingLog =OneTimeWorkRequest.Builder(DeviceIdleStateWorker::class.java).build()
     btnSetAlarm.setOnClickListener {
         workManager.enqueue(sendingLog)
            Toast.makeText(activity, activity?.getString(R.string.set_alarm), Toast.LENGTH_SHORT)
                .show()
        }

        btnStopAlarm.setOnClickListener {
            workManager.cancelAllWork()
            Toast.makeText(activity, activity?.getString(R.string.stop_alarm), Toast.LENGTH_SHORT)
                .show()
        }

    }

}
