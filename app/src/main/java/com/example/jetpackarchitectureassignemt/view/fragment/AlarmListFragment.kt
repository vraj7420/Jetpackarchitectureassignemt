package com.example.jetpackarchitectureassignemt.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.Util
import com.example.jetpackarchitectureassignemt.adapter.RecyclerSetAlarmListAdapter
import com.example.jetpackarchitectureassignemt.databinding.FragmentAlarmListBinding
import com.example.jetpackarchitectureassignemt.model.AlarmListModel
import com.example.jetpackarchitectureassignemt.viewmodel.WorkMangerViewModel
import kotlinx.android.synthetic.main.activity_worker_manger.*
import kotlinx.android.synthetic.main.fragment_alarm_list.*


class AlarmListFragment : Fragment() {
    private val alarmList = ArrayList<AlarmListModel>()
  private lateinit var bindingAlarmListFragment: FragmentAlarmListBinding
  private lateinit var  workerMangerViewModel:WorkMangerViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingAlarmListFragment = DataBindingUtil.inflate(inflater, R.layout.fragment_alarm_list, container, false)
       return bindingAlarmListFragment.root
    }
    private fun viewModelSetUp() {
        workerMangerViewModel = ViewModelProvider(requireActivity()).get(WorkMangerViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelSetUp()
        createAlarmList()
    }
    private fun createAlarmList() {
         alarmList.clear()
        alarmList.add(
            AlarmListModel(
                getString(R.string.charge_mode_alarm),
                getString(R.string.charge_mode_alarm_short_description),
                getString(R.string.charge_mode_alarm_long_description)
            )
        )
        alarmList.add(
            AlarmListModel(
                getString(R.string.battery_low_alarm),
                getString(R.string.battery_low_short_description),
                getString(R.string.battery_low_long_description)
            )
        )
        alarmList.add(
            AlarmListModel(
                getString(R.string.network_state_change),
                getString(R.string.network_change_short_description),
                getString(R.string.network_change_long_description)
            )
        )
        alarmList.add(
            AlarmListModel(
                getString(R.string.device_idle_alarm),
                getString(R.string.device_idle_alarm_short_description),
                getString(R.string.device_idle_alarm_long_description)
            )
        )
        alarmList.add(
            AlarmListModel(
                getString(R.string.gps_alarm),
                getString(R.string.gps_state_change_alarm_short_description),
                getString(R.string.gps_state_change_alarm_long_description)
            )
        )
        alarmList.add(
            AlarmListModel(
                getString(R.string.predefined_time),
                getString(R.string.predefined_time_alarm_short_description),
                getString(R.string.predefined_time_alarm_long_description)
            )
        )
        alarmList.add(
            AlarmListModel(
                getString(R.string.after_certain_amount_time),
                getString(R.string.set_alarm_after_some_time_short_description),
                getString(R.string.set_alarm_after_some_time_long_description)
            )
        )
        setAdapterAlarmList()

    }


    private fun setAdapterAlarmList() {
        val alarmAdapter = RecyclerSetAlarmListAdapter(alarmList){ _, item ->
            workerMangerViewModel.alarmTitle.value=item.alarmTitle
         Util().setFragment(requireActivity().fragment_container_view_work_manger.id,requireContext(),SetAlarmFragment())
        }
        rvAlarmList.adapter = alarmAdapter
    }
}