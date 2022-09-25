package ru.delayvi.drive3.presentation.main_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.delayvi.drive3.domain.usecases.DeleteCarUseCase
import ru.delayvi.drive3.domain.usecases.GetCarListUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val getCarListUseCase: GetCarListUseCase,
    private val deleteCarUseCase: DeleteCarUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getCarListUseCase, deleteCarUseCase) as T
    }
}