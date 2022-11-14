package ru.delayvi.drive3.domain.usecases.cars

import ru.delayvi.drive3.domain.repository.CarListRepository
import javax.inject.Inject

class MakeFavoriteUseCase @Inject constructor(
    private val carListRepository: CarListRepository
) {
    suspend operator fun invoke(carId: Int) = carListRepository.makeFavorite(carId)
}