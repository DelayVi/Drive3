package ru.delayvi.drive3.presentation.car_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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


    fun addCar(car: Car) {
        val carAdd = car.copy(color = Color.GREEN)
        addCarUseCase.addCar(carAdd)
        editingFinished()
    }

    fun editCar(car: Car) {
        val oldCar = getCar(car)
        val newCar = oldCar.copy(
            brand = car.brand,
            model = car.model,
            price = car.price,
            engine = car.engine,
            color = car.color,
            imageUri = car.imageUri
        )
        editCarUseCase.editCar(newCar)
        editingFinished()

    }

    fun getCar(car: Car): Car {
        return getCarUseCase.getCar(car.id)
    }


    private fun editingFinished() {
        _readyToFinish.value = Unit
    }

}