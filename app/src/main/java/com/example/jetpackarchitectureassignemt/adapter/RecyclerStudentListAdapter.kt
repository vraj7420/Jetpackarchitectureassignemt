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

class RecyclerStudentListAdapter(
    private var studentList: ArrayList<StudentModel>,private val btnClick: (itemPosition: Int, item: StudentModel,btnName:String) -> Unit

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
        val student=studentList[position]
        holder.tvStudentName.text=student.studentName
        holder.tvCourse.text = ctx.getString(R.string.course_name)+student.course
        holder.tvContactNumber.text= student.contactNumber.toString()
        holder.tvGender.text=ctx.getString(R.string.gender)+student.gender
        holder.btnUpdate.setOnClickListener { btnClick(position,student,ctx.getString(R.string.update)) }
        holder.btnDelete.setOnClickListener { btnClick(position,student,ctx.getString(R.string.delete)) }
    }

    override fun getItemCount(): Int {
        return studentList.size
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