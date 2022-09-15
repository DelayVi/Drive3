package ru.delayvi.drive3.domain.repository

import androidx.lifecycle.LiveData
import ru.delayvi.drive3.domain.entity.Car

interface CarListRepository {

    fun addCar(car: Car)

    fun deleteCar(car: Car)

    fun editCar(car: Car)

    fun getCar(carId: Int): Car

    fun getCarList(): LiveData<List<Car>>

}