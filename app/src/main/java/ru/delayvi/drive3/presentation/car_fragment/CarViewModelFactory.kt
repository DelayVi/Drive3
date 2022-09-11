package ru.delayvi.drive3.presentation.car_fragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CarViewModelFactory(
    private val carId: Int,
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarFragmentViewModel::class.java)){
            return CarFragmentViewModel() as T
        }
        throw RuntimeException("Unknown viewmodel class $modelClass")
    }
}