package ru.delayvi.drive3.presentation.screens.show_car_fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.delayvi.drive3.domain.entity.cars.Car
import ru.delayvi.drive3.domain.usecases.cars.GetCarUseCase

class ShowCarViewModel (
    val getCarUseCase: GetCarUseCase
) : ViewModel() {

    private val _carItem = MutableLiveData<Car>()
    val carItem: LiveData<Car> = _carItem

    fun getCar(carId: Int) {
        viewModelScope.launch{
            val getCar = getCarUseCase(carId)
            _carItem.value = getCar
            Log.d("MyLog", "getCar")
        }
    }
}