package ru.delayvi.drive3.domain.repository

import androidx.lifecycle.LiveData
import ru.delayvi.drive3.domain.entity.Car

interface CarListRepository {

    suspend fun addCar(car: Car)

    suspend fun deleteCar(car: Car)

    suspend fun editCar(car: Car)

    suspend fun getCar(carId: Int): Car

    suspend fun makeFavorite(carId: Int)

    fun getCarList(): LiveData<List<Car>>

    fun getFavoriteCarList(): LiveData<List<Car>>

}