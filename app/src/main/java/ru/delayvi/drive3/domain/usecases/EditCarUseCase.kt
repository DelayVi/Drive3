package ru.delayvi.drive3.domain.usecases

import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.repository.CarListRepository

class EditCarUseCase(private val carListRepository: CarListRepository) {
    fun editCar(car: Car){
        carListRepository.editCar(car)
    }
}