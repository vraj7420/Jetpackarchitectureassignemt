package com.example.jetpackarchitectureassignemt.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "StudentNew")
class StudentModel(
    @PrimaryKey(autoGenerate = true)
    var studentId: Int=0,
    var studentName: String="",
    var email:String="",
    var contactNumber:Long=0,
    var dob:String="",
    var gender:String="",
    var address:String="",
    var course:String="",
    var HSCPassingYear:String=""
    , var HSCPercentage:String=""
    , var hobbies:String="")