package com.example.jetpackarchitectureassignemt.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RoomViewModelFactory(val ctx:Context):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RoomViewModel(ctx) as T
    }
}