package ru.delayvi.drive3.domain.usecases

import androidx.lifecycle.LiveData
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.repository.CarListRepository
import javax.inject.Inject

class GetCarListUseCase @Inject constructor(
    private val carListRepository: CarListRepository
    ) {
     operator fun invoke() = carListRepository.getCarList()
}