package ru.delayvi.drive3.domain.usecases

import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.repository.CarListRepository

class GetCarUseCase(private val carListRepository: CarListRepository) {
    suspend fun getCar(carId: Int): Car {
       return carListRepository.getCar(carId)
    }
}