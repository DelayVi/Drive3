package ru.delayvi.drive3.presentation.screens.main_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.delayvi.drive3.domain.entity.cars.Car
import ru.delayvi.drive3.domain.usecases.cars.GetCarListUseCase
import ru.delayvi.drive3.domain.usecases.cars.MakeFavoriteUseCase

class MainViewModel(
    private val getCarListUseCase: GetCarListUseCase,
    private val makeFavoriteUseCase: MakeFavoriteUseCase
) : ViewModel() {

    private val _carList = getCarListUseCase()
    val carList: LiveData<List<Car>> = _carList

    fun makeFavorite(carId: Int) {
        viewModelScope.launch {
            makeFavoriteUseCase(carId)
        }
    }

}