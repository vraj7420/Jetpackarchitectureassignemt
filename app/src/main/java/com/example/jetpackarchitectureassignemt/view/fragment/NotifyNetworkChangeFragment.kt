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
import com.example.jetpackarchitectureassignemt.worker_manger.NetworkStateChangeWorker
import kotlinx.android.synthetic.main.fragment_notify_network_change.*


class NotifyNetworkChangeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notify_network_change, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val workManager = WorkManager.getInstance(requireContext())
        val sendingLog = OneTimeWorkRequest.Builder(NetworkStateChangeWorker::class.java).build()

        btnSetAlarm.setOnClickListener {
            workManager.enqueue(sendingLog)
        }
        btnStopAlarm.setOnClickListener {
            workManager.cancelAllWork()
                Toast.makeText(
                    activity,
                    activity?.getString(R.string.set_alarm),
                    Toast.LENGTH_SHORT
                ).show()

        }
    }
}