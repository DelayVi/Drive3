package ru.delayvi.drive3.presentation.screens.favorite_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.delayvi.drive3.domain.entity.cars.Car
import ru.delayvi.drive3.domain.usecases.cars.GetFavoriteCarListUseCase
import ru.delayvi.drive3.domain.usecases.cars.MakeFavoriteUseCase

class FavoriteViewModel(
    private val getFavoriteCarListUseCase: GetFavoriteCarListUseCase,
    private val makeFavoriteUseCase: MakeFavoriteUseCase
) : ViewModel() {

    private val _carList = getFavoriteCarListUseCase()
    val carList: LiveData<List<Car>> = _carList

    fun makeFavorite(carId: Int) {
        viewModelScope.launch {
            makeFavoriteUseCase(carId)
        }
    }

}