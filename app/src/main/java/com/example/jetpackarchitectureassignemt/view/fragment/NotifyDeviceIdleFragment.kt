package com.example.notificationandalarm.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackarchitectureassignemt.R

class NotifyDeviceIdleFragment : Fragment() {
    private lateinit var intentService: Intent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notify_device_idle, container, false)

    }

   /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btnSetAlarm.setOnClickListener {

            intentService = Intent(activity, DeviceIdleStateService::class.java)
            activity?.startService(intentService)
            Toast.makeText(activity, activity?.getString(R.string.set_alarm), Toast.LENGTH_SHORT)
                .show()
        }

        view.btnStopAlarm.setOnClickListener {
            activity?.startService(intentService)
            Toast.makeText(activity, activity?.getString(R.string.stop_alarm), Toast.LENGTH_SHORT)
                .show()

        }

    }
*/
}
