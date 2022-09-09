package ru.delayvi.drive3.domain.usecases

import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.repository.CarListRepository

class AddCarUseCase(private val carListRepository: CarListRepository) {
    fun addCar(car: Car) {
        carListRepository.addCar(car)
    }
}