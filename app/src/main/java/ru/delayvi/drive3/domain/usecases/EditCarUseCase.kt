package ru.delayvi.drive3.domain.usecases

import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.repository.CarListRepository
import javax.inject.Inject

class EditCarUseCase @Inject constructor(
    private val carListRepository: CarListRepository
    ) {
    suspend operator fun invoke(car: Car) = carListRepository.editCar(car)
}