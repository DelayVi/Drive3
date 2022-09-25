package ru.delayvi.drive3.presentation.main_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.delayvi.drive3.data.impl.CarListRepositoryImpl
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.usecases.DeleteCarUseCase
import ru.delayvi.drive3.domain.usecases.GetCarListUseCase

class MainViewModel(
    private val getCarListUseCase: GetCarListUseCase,
    private val deleteCarUseCase: DeleteCarUseCase
) : ViewModel() {

    private val _carList = getCarListUseCase()
    val carList: LiveData<List<Car>> = _carList

    fun deleteCar(car: Car) {
        viewModelScope.launch {
            deleteCarUseCase(car)
        }
    }

}