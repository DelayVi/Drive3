package ru.delayvi.drive3.domain.usecases

import androidx.lifecycle.LiveData
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.repository.CarListRepository

class GetCarListUseCase(private val carListRepository: CarListRepository) {
    fun getCarList():LiveData<List<Car>>{
        return carListRepository.getCarList()
    }
}