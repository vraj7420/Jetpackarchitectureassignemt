package com.example.jetpackarchitectureassignemt.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.model.StudentModel

class RecyclerStudentListAdapter(private val btnDeleteClick: (itemPosition: Int, item: StudentModel) -> Unit,
                                 private var studentList: List<StudentModel>?, private val btnClick: (itemPosition: Int, item: StudentModel) -> Unit

): RecyclerView.Adapter<RecyclerStudentListAdapter.StudentListViewHolder>() {

     private lateinit var ctx:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentListViewHolder {
        ctx=parent.context
        val recyclerInflater = LayoutInflater.from(parent.context)
        val recyclerView = recyclerInflater.inflate(R.layout.item_student_list, parent, false)
        return  StudentListViewHolder(recyclerView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StudentListViewHolder, position: Int) {
        val student= studentList?.get(position)
        holder.tvStudentName.text=  ctx.getString(R.string.student_name)+student?.studentName
        holder.tvCourse.text  = ctx.getString(R.string.course_name)+ student?.course
        holder.tvContactNumber.text= ctx.getString(R.string.contact_number)+student?.contactNumber?.toString()
        holder.tvGender.text=ctx.getString(R.string.gender)+student?.gender
        holder.btnUpdate.setOnClickListener {
            if (student != null) {
                btnClick(position,student)
            }
        }
        holder.btnDelete.setOnClickListener {
            if (student != null) {
                btnDeleteClick(position,student)
            }
        }
    }

    override fun getItemCount(): Int {
        return studentList?.size?:0
    }

    inner class StudentListViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var tvStudentName: TextView = itemView.findViewById(R.id.tvStudentName)
        var tvCourse:TextView = itemView.findViewById(R.id.tvCourseName)
        var tvContactNumber: TextView = itemView.findViewById(R.id.tvContactNumber)
        var tvGender: TextView = itemView.findViewById(R.id.tvGender)
         var btnUpdate:ImageButton=itemView.findViewById(R.id.btnUpdate)
         var btnDelete:ImageButton=itemView.findViewById(R.id.btnDelete)
     }
}