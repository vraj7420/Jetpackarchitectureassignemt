package com.example.jetpackarchitectureassignemt.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackarchitectureassignemt.database.StudentDatabase
import com.example.jetpackarchitectureassignemt.model.StudentModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomViewModel: ViewModel() {
    var addOrUpdate= MutableLiveData<Boolean>()
    var studentData= MutableLiveData<StudentModel>()
    var id=MutableLiveData<Int>()
     private lateinit var student:LiveData<List<StudentModel>>


    fun studentData(ctx:Context): LiveData<List<StudentModel>> {
        val studentDatabase = StudentDatabase.getDatabase(ctx)
        student= studentDatabase?.studentDao()?.getStudent()!!
        return student
    }

    fun deleteStudentData(ctx: Context,student: StudentModel): Int? {
        val studentDatabase = StudentDatabase.getDatabase(ctx)
           return studentDatabase?.studentDao()?.deleteStudent(student)
    }
    fun  insertData(ctx: Context,student: StudentModel){
        val studentDatabase = StudentDatabase.getDatabase(ctx)
        GlobalScope.launch {
        studentDatabase?.studentDao()?.insertStudent(student)
        }
    }

    fun  updateStudentData(ctx: Context,student: StudentModel){
        val studentDatabase = StudentDatabase.getDatabase(ctx)
        GlobalScope.launch {
            studentDatabase?.studentDao()?.updateStudent(student)
        }
    }

}

