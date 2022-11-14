package ru.delayvi.drive3.presentation.screens.main_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.delayvi.drive3.domain.usecases.cars.GetCarListUseCase
import ru.delayvi.drive3.domain.usecases.cars.MakeFavoriteUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val getCarListUseCase: GetCarListUseCase,
    private val makeFavoriteUseCase: MakeFavoriteUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getCarListUseCase, makeFavoriteUseCase) as T
    }
}