package com.example.jetpackarchitectureassignemt.view.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.worker_manger.DeviceIdleStateWorker
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_notify_device_idle.*

class NotifyDeviceIdleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notify_device_idle, container, false)

    }

 @RequiresApi(Build.VERSION_CODES.M)
 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          init()
 }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun init(){
        val constraints: Constraints = Constraints.Builder().setRequiresDeviceIdle(true).build()
        val workManager = WorkManager.getInstance(requireContext())
        val sendingLog = OneTimeWorkRequest.Builder(DeviceIdleStateWorker::class.java).setConstraints(constraints).build()
        btnSetAlarm.setOnClickListener {
            workManager.enqueue(sendingLog)
            Snackbar.make(requireActivity().findViewById(android.R.id.content),requireContext().getString(R.string.set_alarm), Snackbar.LENGTH_LONG).show()
        }

        btnStopAlarm.setOnClickListener {
            workManager.cancelAllWork()
            Snackbar.make(requireActivity().findViewById(android.R.id.content),requireContext().getString(R.string.stop_alarm), Snackbar.LENGTH_LONG).show()
        }
    }

}
