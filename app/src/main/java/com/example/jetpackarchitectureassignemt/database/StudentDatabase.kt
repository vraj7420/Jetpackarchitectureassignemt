package com.example.jetpackarchitectureassignemt.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetpackarchitectureassignemt.Util
import com.example.jetpackarchitectureassignemt.model.StudentModel


@Database(entities = [StudentModel::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDAO

    companion object {
        @Volatile
        private var instance: StudentDatabase? = null
        fun getDatabase(ctx: Context): StudentDatabase?{
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(ctx, StudentDatabase::class.java,Util.dataBase)
                        .build()
                }
            }
            return instance
        }
    }
}