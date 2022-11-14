package ru.delayvi.drive3.presentation.screens.show_car_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.delayvi.drive3.domain.usecases.cars.GetCarUseCase
import javax.inject.Inject

class ShowCarViewModelFactory @Inject constructor(
    val getCarUseCase: GetCarUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShowCarViewModel(getCarUseCase) as T
    }
}