package com.example.jetpackarchitectureassignemt.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.worker_manger.GpsEnableDisableWorker
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_notify_gps_state_change.*

class NotifyGpsStateChangeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notify_gps_state_change, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val workManager = WorkManager.getInstance(requireContext())
        val sendingLog = OneTimeWorkRequest.Builder(GpsEnableDisableWorker::class.java).build()
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
