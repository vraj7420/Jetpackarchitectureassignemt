package com.example.jetpackarchitectureassignemt.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackarchitectureassignemt.database.StudentDatabase
import com.example.jetpackarchitectureassignemt.model.StudentModel
import kotlinx.coroutines.launch

class RoomViewModel(var ctx: Context) : ViewModel() {
    var addOrUpdate = MutableLiveData<Boolean>()
    var studentData = MutableLiveData<StudentModel>()
    var id = MutableLiveData<Long>()
    var studentName = MutableLiveData("")
    var studentNameError = MutableLiveData<String>()
    var email = MutableLiveData("")
    var emailError = MutableLiveData<String>()
    var contactNumber = MutableLiveData("")
    var contactNumberError = MutableLiveData<String>()
    var dob = MutableLiveData("")
    var dobError = MutableLiveData<String>()
    var gender = ""
    var address = MutableLiveData("")
    var addressError = MutableLiveData<String>()
    var course = MutableLiveData("")
    var hSCPassingYear = MutableLiveData("")
    var hSCPassingYearError = MutableLiveData<String>()
    var hscPercentageError = MutableLiveData<String>()
    var hSCPercentage = MutableLiveData("")
    var hobbies = ""
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    var selectedCoursePosition = MutableLiveData(0)
    private lateinit var student: LiveData<List<StudentModel>>
    private var studentDatabase: StudentDatabase? = null


    init {
        studentDatabase = StudentDatabase.getDatabase(ctx)
    }

    fun studentData(): LiveData<List<StudentModel>> {
        student = studentDatabase?.studentDao()?.getStudent() ?: student
        return student
    }

    fun deleteStudentData(student: StudentModel): Int? {
        return studentDatabase?.studentDao()?.deleteStudent(student)
    }

    fun insertData(){
        viewModelScope.launch {

                studentDatabase?.studentDao()?.insertStudent(
                    StudentModel(
                        0, studentName.value.toString(),
                        email.value.toString(),
                        contactNumber.value.toString(),
                        dob.value.toString(),
                        gender,
                        address.value.toString(),
                        course.value.toString(),
                        hSCPassingYear.value.toString(),
                        hSCPercentage.value.toString(),
                        hobbies
                    )
                )
             }
    }

    fun updateStudentData() {
        viewModelScope.launch {
            studentDatabase?.studentDao()?.updateStudent(
                StudentModel(
                    id.value ?: 0, studentName.value.toString(),
                    email.value.toString(),
                    contactNumber.value.toString(),
                    dob.value.toString(),
                    gender,
                    address.value.toString(),
                    course.value.toString(),
                    hSCPassingYear.value.toString(),
                    hSCPercentage.value.toString(),
                    hobbies
                )
            )
        }
    }

    fun getDataForUpdate() {
        id.value = studentData.value?.studentId
        studentName.value = studentData.value?.studentName
        email.value = studentData.value?.email
        contactNumber.value = studentData.value?.contactNumber
        dob.value = studentData.value?.dob
        gender = studentData.value?.gender.toString()
        address.value = studentData.value?.address
        course.value = studentData.value?.course
        hSCPassingYear.value = studentData.value?.HSCPassingYear
        hSCPercentage.value = studentData.value?.HSCPercentage
        hobbies = studentData.value?.hobbies.toString()
    }

    /*private fun validateStudentData(): Boolean {
        when {
            (studentName.value.toString().trim().isEmpty()) -> {
                studentNameError.value = ctx.getString(R.string.error_full_name)
                return false
            }
            (email.value.toString().trim().isEmpty()) -> {
                emailError.value = ctx.getString(R.string.error_email_is_empty)
                return false
            }
            (!email.value.toString().trim().matches(emailPattern.toRegex())) -> {
                emailError.value = ctx.getString(R.string.error_email_valid)
                return false
            }
            (contactNumber.value.toString().trim().isEmpty()) -> {
                contactNumberError.value = ctx.getString(R.string.error_phone_number_is_empty)
                return false
            }
            (contactNumber.value.toString().length != 10) -> {
                contactNumberError.value = ctx.getString(R.string.error_phone_number_valid)
                return false
            }
            (dob.value.toString().trim().isEmpty()) -> {
                dobError.value = ctx.getString(R.string.error_birth_is_empty)
                return false
            }
            (address.toString().trim().isEmpty()) -> {
                addressError.value = ctx.getString(R.string.error_address)
                return false
            }
            (hSCPassingYear.value.toString().isEmpty()) -> {
                hSCPassingYearError.value = ctx.getString(R.string.error_passing_year)
                return false
            }
            (hSCPercentage.value.toString().trim().isEmpty()) -> {
                hscPercentageError.value = ctx.getString(R.string.error_percentage)
                return false
            }
            (hSCPercentage.value.toString().toFloat() > 100.00) -> {
                hscPercentageError.value = ctx.getString(R.string.error_not_valid_percentage)
                return false
            }

        }
        studentNameError.postValue("")
        emailError.postValue("")
        contactNumberError.postValue("")
        dobError.postValue("")
        addressError.postValue("")
        hSCPassingYearError.postValue("")
        hscPercentageError.postValue("")
        return true
    }*/
}

