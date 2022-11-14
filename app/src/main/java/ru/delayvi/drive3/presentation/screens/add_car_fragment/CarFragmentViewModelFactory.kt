package ru.delayvi.drive3.presentation.screens.add_car_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.delayvi.drive3.domain.usecases.cars.AddCarUseCase
import ru.delayvi.drive3.domain.usecases.cars.EditCarUseCase
import ru.delayvi.drive3.domain.usecases.cars.GetCarUseCase
import javax.inject.Inject

class CarFragmentViewModelFactory @Inject constructor(
    private val addCarUseCase: AddCarUseCase,
    private val editCarUseCase: EditCarUseCase,
    private val getCarUseCase: GetCarUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CarFragmentViewModel(addCarUseCase, editCarUseCase, getCarUseCase) as T
    }
}