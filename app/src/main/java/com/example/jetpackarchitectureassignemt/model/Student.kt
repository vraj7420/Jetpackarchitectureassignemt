package com.example.jetpackarchitectureassignemt.model

class Student(
    var studentId: Long=0,
    var studentName: String="",
    var email:String="",
    var contactNumber:String ="",
    var dob:String="",
    var gender:String="",
    var address:String="",
    var course:String="",
    var HSCPassingYear:String=""
    , var HSCPercentage:String=""
    , var hobbies:String="") {
    constructor(student: StudentModel) : this(
        studentId=student.studentId,
         studentName=student.studentName,
        email=student.email,
     contactNumber=student.contactNumber,
        dob=student.dob,
        gender=student.gender,
        address=student.address,
        course=student.course,
        HSCPassingYear=student.HSCPassingYear,
        HSCPercentage=student.HSCPercentage,
        hobbies=student.hobbies
    )
}