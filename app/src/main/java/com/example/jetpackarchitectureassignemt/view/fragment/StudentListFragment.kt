package com.example.jetpackarchitectureassignemt.view.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.Util
import com.example.jetpackarchitectureassignemt.adapter.RecyclerStudentListAdapter
import com.example.jetpackarchitectureassignemt.databinding.FragmentStudentListBinding
import com.example.jetpackarchitectureassignemt.model.StudentModel
import com.example.jetpackarchitectureassignemt.viewmodel.RoomViewModel
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.android.synthetic.main.fragment_student_list.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@DelicateCoroutinesApi
class StudentListFragment : Fragment() {
    private lateinit var bindingStudentList: FragmentStudentListBinding
    private lateinit var adapter: RecyclerStudentListAdapter
    private lateinit var roomViewModel: RoomViewModel
    private lateinit var alertDialogDelete: AlertDialog
    private  var delete=0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingStudentList =
            DataBindingUtil.inflate(inflater, R.layout.fragment_student_list, container, false)
        return bindingStudentList.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelSetUp()
        dataObserver()
        setListener()
    }

    private fun viewModelSetUp() {
        roomViewModel = ViewModelProvider(requireActivity()).get(RoomViewModel::class.java)
    }

    private fun dataObserver() {
        roomViewModel.studentData(requireContext()).observe(viewLifecycleOwner, {
            setAdapter(it)
        })
    }


    private fun setAdapter(studentList: List<StudentModel>) {
        rvStudentList.adapter = null
        if(studentList.isEmpty()){
            rvStudentList.visibility=View.GONE
            tvNoData.visibility=View.VISIBLE
        }
        adapter = RecyclerStudentListAdapter({ _, item ->
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle(requireContext().getString(R.string.delete))
            builder.setMessage(requireContext().getString(R.string.delete_message))
            builder.setPositiveButton(requireContext().getString(R.string.yes)) { dialog: DialogInterface?, _: Int ->
                GlobalScope.launch {
                delete= roomViewModel.deleteStudentData(requireContext(), item) ?:0
                }
                if(delete==1){
                    dialog?.dismiss()
                }
            }
            builder.setNegativeButton(requireContext().getString(R.string.no)) { dialog: DialogInterface, _: Int -> dialog.cancel() }
            alertDialogDelete = builder.create()
            alertDialogDelete.show()
        }, studentList) { _, item ->
            roomViewModel.addOrUpdate.value = false
            roomViewModel.studentData.value = item
            roomViewModel.id.value = item.studentId
            Util().setFragment(
                requireActivity().fragment_container_view_room.id,
                requireContext(),
                StudentDetailsFragment()
            )
        }
        rvStudentList.adapter = adapter
        rvStudentList.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setListener() {
        fabRegisterStudent.setOnClickListener {
            roomViewModel.addOrUpdate.value = true
            Util().setFragment(
                requireActivity().fragment_container_view_room.id,
                requireContext(),
                StudentDetailsFragment())
        }
    }
}