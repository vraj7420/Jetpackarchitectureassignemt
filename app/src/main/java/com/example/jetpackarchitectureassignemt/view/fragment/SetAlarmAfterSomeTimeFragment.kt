package com.example.jetpackarchitectureassignemt.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.worker_manger.AfterSomeTimeAlarm
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_set_alarm_after_some_time.*
import java.util.concurrent.TimeUnit


class SetAlarmAfterSomeTimeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_set_alarm_after_some_time, container, false)

    }

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun  init(){
        val workManager = WorkManager.getInstance(requireContext())
        btnSetAlarm.setOnClickListener {
            if (tetRepeatingAlarmTime.text?.isEmpty() == true) {
                tetRepeatingAlarmTime.error = requireContext().getString(R.string.repeating_alarm_error)
                tetRepeatingAlarmTime.requestFocus()
            } else {
                workManager.enqueue(PeriodicWorkRequest.Builder(AfterSomeTimeAlarm::class.java,tetRepeatingAlarmTime.text.toString().toLong(), TimeUnit.MINUTES).build())
                Snackbar.make(requireActivity().findViewById(android.R.id.content),requireContext().getString(R.string.set_alarm), Snackbar.LENGTH_LONG).show()
            }
        }

        btnStopAlarm.setOnClickListener {
            workManager.cancelAllWork()
            Snackbar.make(requireActivity().findViewById(android.R.id.content),requireContext().getString(R.string.stop_alarm), Snackbar.LENGTH_LONG).show()

        }
    }

}