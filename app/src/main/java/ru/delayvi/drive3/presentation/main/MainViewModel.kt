package ru.delayvi.drive3.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.delayvi.drive3.data.impl.CarListRepositoryImpl
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.usecases.DeleteCarUseCase
import ru.delayvi.drive3.domain.usecases.GetCarListUseCase

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CarListRepositoryImpl(application)

    private val getCarListUseCase = GetCarListUseCase(repository)
    private val deleteCarUseCase = DeleteCarUseCase(repository)

    private val _carList = getCarListUseCase.getCarList()
    val carList: LiveData<List<Car>> = _carList


    fun deleteCar(car: Car) {
        viewModelScope.launch {
            deleteCarUseCase.deleteCar(car)
        }
    }

}