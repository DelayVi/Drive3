package ru.delayvi.drive3.presentation.car_fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.entity.Color
import ru.delayvi.drive3.domain.usecases.AddCarUseCase
import ru.delayvi.drive3.domain.usecases.EditCarUseCase
import ru.delayvi.drive3.domain.usecases.GetCarUseCase

class CarFragmentViewModel(
    private val addCarUseCase: AddCarUseCase,
    private val editCarUseCase: EditCarUseCase,
    private val getCarUseCase: GetCarUseCase
) : ViewModel() {

    private val _readyToFinish = MutableLiveData<Unit>()
    val readyToFinish: LiveData<Unit> = _readyToFinish

    private val _carItem = MutableLiveData<Car>()
    val carItem: LiveData<Car> = _carItem

    fun addCar(car: Car) {
        viewModelScope.launch {
            val carAdd = car.copy(color = Color.GREEN)
            addCarUseCase(carAdd)
            editingFinished()
        }
    }

    fun editCar(car: Car) {
        viewModelScope.launch {
            val oldCar = carItem.value
            val newCar = oldCar?.copy(
                brand = car.brand,
                model = car.model,
                price = car.price,
                engine = car.engine,
                color = car.color,
                imageUri = car.imageUri
            )
            newCar?.let { editCarUseCase(it) }
            editingFinished()
        }
    }

    fun getCar(carId: Int) {
        viewModelScope.launch{
            val getCar = getCarUseCase(carId)
            _carItem.value = getCar
            Log.d("MyLog", "getCar")
        }
    }


    private fun editingFinished() {
        _readyToFinish.value = Unit
    }

}