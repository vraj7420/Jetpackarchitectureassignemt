package com.example.jetpackarchitectureassignemt.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.model.AlarmListModel

class RecyclerSetAlarmListAdapter(
    private var setAlarmListModel: ArrayList<AlarmListModel>,
    private val itemClick: (itemPosition: Int, item: AlarmListModel) -> Unit
) :
    RecyclerView.Adapter<RecyclerSetAlarmListAdapter.SetNotificationAlarmViewHolder>() {
    private lateinit var ctx: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SetNotificationAlarmViewHolder {
       ctx=parent.context
        val recyclerInflater = LayoutInflater.from(ctx)
        val recyclerView =
            recyclerInflater.inflate(R.layout.item_of_alarm_list_layout, parent, false)
        return SetNotificationAlarmViewHolder(recyclerView)

    }


    override fun onBindViewHolder(holder: SetNotificationAlarmViewHolder, position: Int) {
        val alarmList = setAlarmListModel[position]
        holder.alarmTitle.text = alarmList.alarmTitle
        holder.alarmShortDescription.text = alarmList.alarmShortDescription
        holder.alarmLongDescription.text = alarmList.alarmLongDescription
        holder.itemView.setOnClickListener { itemClick(position,alarmList) }
    }


    override fun getItemCount(): Int {
        return setAlarmListModel.size
    }


    inner class SetNotificationAlarmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var alarmTitle: TextView = itemView.findViewById(R.id.tvSetAlarm)
        var alarmShortDescription: TextView = itemView.findViewById(R.id.tvSetAlarmShortDescription)
        var alarmLongDescription: TextView = itemView.findViewById(R.id.tvAlarmLongDescription)

    }
}