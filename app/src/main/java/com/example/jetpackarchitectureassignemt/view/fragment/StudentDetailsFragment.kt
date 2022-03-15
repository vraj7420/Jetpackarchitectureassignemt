package com.example.jetpackarchitectureassignemt.view.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.Util
import com.example.jetpackarchitectureassignemt.databinding.FragmentStudentDetailsBinding
import com.example.jetpackarchitectureassignemt.viewmodel.RoomViewModel
import com.example.jetpackarchitectureassignemt.viewmodel.RoomViewModelFactory
import com.whiteelephant.monthpicker.MonthPickerDialog
import kotlinx.android.synthetic.main.fragment_student_details.*
import kotlinx.coroutines.DelicateCoroutinesApi
import java.text.SimpleDateFormat
import java.util.*


@DelicateCoroutinesApi
class StudentDetailsFragment : Fragment() {
    private lateinit var bindingStudentDetailsFragment: FragmentStudentDetailsBinding
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private var userGender: String = ""
    private var hobbies: String = ""
    private var calendar = Calendar.getInstance()
    private var birthDate: DatePickerDialog.OnDateSetListener? = null
    private val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.UK)
    private lateinit var roomViewModel: RoomViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingStudentDetailsFragment = DataBindingUtil.inflate(inflater, R.layout.fragment_student_details, container, false)
        return bindingStudentDetailsFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelSetUp()
        init()
        observer()
    }

    private fun observer(){
        roomViewModel.selectedCoursePosition.observe(viewLifecycleOwner,{
            roomViewModel.course.value= spinnerCourseSelection.selectedItem.toString()
        })
    }

    private fun viewModelSetUp() {
        roomViewModel = ViewModelProvider(requireActivity(), RoomViewModelFactory(requireContext()))[RoomViewModel::class.java]
        bindingStudentDetailsFragment.student=roomViewModel
        bindingStudentDetailsFragment.lifecycleOwner=this
    }

    private fun init() {
        rgGender.setOnCheckedChangeListener { _, i ->
            when(i){
                rbMale.id->userGender=rbMale.text.toString()
                rbFemale.id->userGender=rbFemale.text.toString()
                rbOther.id->userGender=rbOther.text.toString()
            }
        }
        val courseSelectionAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.courses)
        )
        birthDate = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            tetDateOfBirth.setText(sdf.format(calendar.time))
        }
        tetDateOfBirth.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                birthDate,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

        tetPassingYear.setOnClickListener {
            val year = MonthPickerDialog.Builder(requireContext(), { _, selectedYear ->
                tetPassingYear.setText(selectedYear.toString())
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH))
            year.setActivatedYear(calendar.get(Calendar.YEAR))
                .setMinYear(2000)
                .setMaxYear(2022)
                .showYearOnly()
                .setTitle(Util.selectYear)
                .build().show()
        }
        spinnerCourseSelection.adapter = courseSelectionAdapter
        val addOrUpdate = roomViewModel.addOrUpdate.value
        if (addOrUpdate != true) {
            btnAddOrUpdate.text = requireContext().getString(R.string.update)
            setListenerForUpdate()
        }
        else {
            btnAddOrUpdate.text = requireContext().getString(R.string.add)
            setListenerForAdd()
        }
    }

    private fun setListenerForUpdate() {
        setData()
        btnAddOrUpdate.setOnClickListener {
            if (validateStudentData()) {
                roomViewModel.updateStudentData()
                Toast.makeText(
                    requireContext(),
                    getString(R.string.update_data),
                    Toast.LENGTH_SHORT
                ).show()
                requireActivity().onBackPressed()
            }
        }
    }

    private fun setListenerForAdd() {
        btnAddOrUpdate.setOnClickListener {
            if (validateStudentData()) {
                    roomViewModel.insertData()
                Toast.makeText(
                    requireContext(),
                    getString(R.string.inset_data),
                    Toast.LENGTH_SHORT
                ).show()
                requireActivity().onBackPressed()

            }
        }
    }
    private fun validateStudentData(): Boolean {
        when {
            (tetStudentName.text.toString().trim().isEmpty()) -> {
                tetStudentName.error = getString(R.string.error_full_name)
                tetStudentName.requestFocus()
                return false
            }
            (tetEmailAddress.text.toString().trim().isEmpty()) -> {
                tetEmailAddress.error = getString(R.string.error_email_is_empty)
                tetEmailAddress.requestFocus()
                return false
            }
            (!tetEmailAddress.text.toString().trim().matches(emailPattern.toRegex())) -> {
                tetEmailAddress.error = getString(R.string.error_email_valid)
                tetEmailAddress.requestFocus()
                return false
            }
            (tetMobileNumber.text.toString().trim().isEmpty()) -> {
                tetMobileNumber.error = getString(R.string.error_phone_number_is_empty)
                tetMobileNumber.requestFocus()
                return false
            }
            (tetMobileNumber.text.toString().length != 10) -> {
                tetMobileNumber.error = getString(R.string.error_phone_number_valid)
                tetMobileNumber.requestFocus()
                return false
            }
            (tetDateOfBirth.text.toString().trim().isEmpty()) -> {
                tetDateOfBirth.error = getString(R.string.error_birth_is_empty)
                tetDateOfBirth.requestFocus()
                return false
            }
            (tetAddress.text.toString().trim().isEmpty()) -> {
                tetAddress.error = getString(R.string.error_address)
                tetAddress.requestFocus()
                return false
            }
            (tetPassingYear.text.toString().isEmpty()) -> {
                tetPassingYear.error = getString(R.string.error_passing_year)
                tetPassingYear.requestFocus()
                return false
            }
            (tetHscPercentage.text.toString().trim().isEmpty()) -> {
                tetHscPercentage.error = getString(R.string.error_percentage)
                tetHscPercentage.requestFocus()
                return false
            }
            (tetHscPercentage.text.toString().toFloat() > 100.00) -> {
                tetHscPercentage.error = getString(R.string.error_not_valid_percentage)
                tetHscPercentage.requestFocus()
                return false
            }

        }
        return true
    }

    private fun setData() {
        val studentDataForUpdate = roomViewModel.studentData.value
        val indexOfCourse: Int = resources.getStringArray(R.array.courses).indexOf(studentDataForUpdate?.course)
        spinnerCourseSelection.setSelection(indexOfCourse)
        val updateBirthDate= sdf.parse(studentDataForUpdate?.dob ?:" ")
        calendar.time = updateBirthDate ?: Date()
        birthDate = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            tetDateOfBirth.setText(sdf.format(calendar.time))
        }
        tetDateOfBirth.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                birthDate,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        tetPassingYear.setOnClickListener {
            val yearFormat = SimpleDateFormat(Util.yearFormat, Locale.US)
            val yearUpdate = yearFormat.parse(studentDataForUpdate?.HSCPassingYear ?:"")
            calendar.time = yearUpdate?:Date()
            val year = MonthPickerDialog.Builder(requireContext(), { _, selectedYear ->
                tetPassingYear.setText(selectedYear.toString())
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH))
            year.setActivatedYear(calendar.get(Calendar.YEAR))
                .setMinYear(2000)
                .setMaxYear(2022)
                .showYearOnly()
                .setTitle(Util.selectYear)
                .build().show()
        }
    }
}
