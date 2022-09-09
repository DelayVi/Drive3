package ru.delayvi.drive3.presentation

import androidx.lifecycle.ViewModel
import ru.delayvi.drive3.data.impl.CarListRepositoryImpl
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.usecases.DeleteCarUseCase
import ru.delayvi.drive3.domain.usecases.GetCarListUseCase
import ru.delayvi.drive3.domain.usecases.GetCarUseCase

class MainViewModel : ViewModel() {
    private val repository = CarListRepositoryImpl

    private val getCarListUseCase = GetCarListUseCase(repository)
    private val deleteCarUseCase = DeleteCarUseCase(repository)
    private val getCarUseCase = GetCarUseCase(repository)

    val carList = getCarListUseCase.getCarList()

    fun getCar(carId: Int): Car {
        return getCarUseCase.getCar(carId)
    }

    fun deleteCar(car: Car) {
        deleteCarUseCase.deleteCar(car)
    }
}