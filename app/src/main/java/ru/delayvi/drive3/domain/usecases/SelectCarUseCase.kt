package ru.delayvi.drive3.domain.usecases

import androidx.lifecycle.LiveData
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.repository.CarListRepository

class SelectCarUseCase(private val carListRepository: CarListRepository) {
    fun selectCar(car: Car) {
       carListRepository.selectCar(car)
    }
}