package com.example.jetpackarchitectureassignemt.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackarchitectureassignemt.model.StudentModel

class RoomViewModel: ViewModel() {
    var addOrUpdate= MutableLiveData<String>()
    var studentData= MutableLiveData<StudentModel>()
    var id=MutableLiveData<Int>()

}