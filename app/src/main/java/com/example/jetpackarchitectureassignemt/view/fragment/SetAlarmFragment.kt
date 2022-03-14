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
import com.example.jetpackarchitectureassignemt.databinding.FragmentSetAlarmBinding
import com.example.jetpackarchitectureassignemt.viewmodel.WorkMangerViewModel
import kotlinx.android.synthetic.main.fragment_set_alarm.*


class SetAlarmFragment : Fragment() {
    private lateinit var bindingSetAlarmFragment: FragmentSetAlarmBinding
    private lateinit var workerMangerViewModel: WorkMangerViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingSetAlarmFragment =
            DataBindingUtil.inflate(inflater, R.layout.fragment_set_alarm, container, false)
        return bindingSetAlarmFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelSetUp()
        setFragment()
    }

    private fun viewModelSetUp() {
        workerMangerViewModel =
            ViewModelProvider(requireActivity()).get(WorkMangerViewModel::class.java)
    }

    private fun setFragment() {
        val alarmTitle = workerMangerViewModel.alarmTitle.value
        tvAlarmTitle.text = alarmTitle
        when (alarmTitle) {
            getString(R.string.charge_mode_alarm) ->
                Util().setFragment(fragmentAlarm.id, requireContext(), NotifyChargeModeFragment())
            getString(R.string.battery_low_alarm) -> Util().setNestedFragment(
                fragmentAlarm.id,
                requireContext(),
                NotifyBatteryLowFragment()
            )

            getString(R.string.network_state_change) -> Util().setNestedFragment(
                fragmentAlarm.id,
                requireContext(),
                NotifyNetworkChangeFragment()
            )

            getString(R.string.gps_alarm) -> Util().setNestedFragment(
                fragmentAlarm.id,
                requireContext(),
                NotifyGpsStateChangeFragment()
            )

            getString(R.string.device_idle_alarm) -> Util().setNestedFragment(
                fragmentAlarm.id,
                requireContext(),
                NotifyDeviceIdleFragment()
            )

            getString(R.string.predefined_time) -> Util().setNestedFragment(
                fragmentAlarm.id,
                requireContext(),
                SetPredefinedTimeAlarmFragment()
            )

            getString(R.string.after_certain_amount_time) -> Util().setNestedFragment(
                fragmentAlarm.id,
                requireContext(),
                SetAlarmAfterSomeTimeFragment()
            )

        }
    }
}