package com.example.jetpackarchitectureassignemt.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jetpackarchitectureassignemt.model.StudentModel

@Dao
interface StudentDAO {
    @Insert
    suspend fun insertStudent(student: StudentModel)
   /*@Query("UPDATE student SET studentName:=st")
   fun updateStudent(vararg student: StudentModel)
*/
   @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateStudent(vararg student: StudentModel):Int

    @Query("SELECT * FROM Student")
    fun getStudent(): LiveData<List<StudentModel>>

    @Delete
    fun deleteStudent(vararg student: StudentModel):Int
}