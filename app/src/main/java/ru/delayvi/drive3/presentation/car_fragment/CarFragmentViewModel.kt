package ru.delayvi.drive3.presentation.car_fragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.delayvi.drive3.data.impl.CarListRepositoryImpl
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.entity.Color
import ru.delayvi.drive3.domain.usecases.AddCarUseCase
import ru.delayvi.drive3.domain.usecases.EditCarUseCase
import ru.delayvi.drive3.domain.usecases.GetCarUseCase

class CarFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CarListRepositoryImpl(application)

    private val addCarUseCase = AddCarUseCase(repository)
    private val editCarUseCase = EditCarUseCase(repository)
    private val getCarUseCase = GetCarUseCase(repository)

    private val _readyToFinish = MutableLiveData<Unit>()
    val readyToFinish: LiveData<Unit> = _readyToFinish

    private val _carItem = MutableLiveData<Car>()
    val carItem: LiveData<Car> = _carItem

    fun addCar(car: Car) {
        viewModelScope.launch {
            val carAdd = car.copy(color = Color.GREEN)
            addCarUseCase.addCar(carAdd)
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
            newCar?.let { editCarUseCase.editCar(it) }
            editingFinished()
        }
    }

    fun getCar(carId: Int) {
        viewModelScope.launch{
            val getCar = getCarUseCase.getCar(carId)
            _carItem.value = getCar
            Log.d("MyLog", "getCar")
        }
    }


    private fun editingFinished() {
        _readyToFinish.value = Unit
    }

}