package ru.delayvi.drive3.domain.usecases

import ru.delayvi.drive3.domain.repository.CarListRepository
import javax.inject.Inject

class GetCarUseCase @Inject constructor(
    private val carListRepository: CarListRepository
    ) {
    suspend operator fun invoke(carId: Int) = carListRepository.getCar(carId)
}