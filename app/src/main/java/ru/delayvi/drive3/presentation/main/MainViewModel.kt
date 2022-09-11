package ru.delayvi.drive3.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.delayvi.drive3.data.impl.CarListRepositoryImpl
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.usecases.DeleteCarUseCase
import ru.delayvi.drive3.domain.usecases.GetCarListUseCase
import ru.delayvi.drive3.domain.usecases.GetSelectedCar
import ru.delayvi.drive3.domain.usecases.SelectCarUseCase

class MainViewModel : ViewModel() {
    private val repository = CarListRepositoryImpl

    private val getCarListUseCase = GetCarListUseCase(repository)
    private val deleteCarUseCase = DeleteCarUseCase(repository)
    private val selectCarUseCase = SelectCarUseCase(repository)


    private val _carList = getCarListUseCase.getCarList()
    val carList: LiveData<List<Car>> = _carList


    fun selectCar(car: Car) {
        selectCarUseCase.selectCar(car)
    }


    fun deleteCar(car: Car) {
        deleteCarUseCase.deleteCar(car)
    }
}