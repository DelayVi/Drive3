package ru.delayvi.drive3.domain.usecases.cars

import ru.delayvi.drive3.domain.entity.cars.Car
import ru.delayvi.drive3.domain.repository.CarListRepository
import javax.inject.Inject

class AddCarUseCase @Inject constructor(
    private val carListRepository: CarListRepository
    ) {
    suspend operator fun invoke(car: Car) = carListRepository.addCar(car)
}