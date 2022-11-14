package ru.delayvi.drive3.domain.usecases.cars

import ru.delayvi.drive3.domain.repository.CarListRepository
import javax.inject.Inject

class GetFavoriteCarListUseCase@Inject constructor(
    private val carListRepository: CarListRepository
) {
    operator fun invoke() = carListRepository.getFavoriteCarList()
}