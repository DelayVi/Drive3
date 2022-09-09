package ru.delayvi.drive3.domain.usecases

import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.repository.CarListRepository

class DeleteCarUseCase(private val carListRepository: CarListRepository) {
    fun deleteCar(car: Car){
        carListRepository.deleteCar(car)
    }
}