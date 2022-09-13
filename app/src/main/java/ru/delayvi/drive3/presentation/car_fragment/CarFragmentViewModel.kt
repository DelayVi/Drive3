package ru.delayvi.drive3.presentation.car_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.delayvi.drive3.data.impl.CarListRepositoryImpl
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.entity.Color
import ru.delayvi.drive3.domain.usecases.AddCarUseCase
import ru.delayvi.drive3.domain.usecases.EditCarUseCase
import ru.delayvi.drive3.domain.usecases.GetSelectedCar

class CarFragmentViewModel : ViewModel() {
    private val repository = CarListRepositoryImpl

    private val addCarUseCase = AddCarUseCase(repository)
    private val editCarUseCase = EditCarUseCase(repository)
    private val getSelectedCar = GetSelectedCar(repository)



    private val _readyToFinish = MutableLiveData<Unit>()
    val readyToFinish: LiveData<Unit> = _readyToFinish

    private var _selectedCar = getSelectedCar.getSelectedCar()
    val selectedCar: LiveData<Car> = _selectedCar

    fun addCar(car: Car){
        val carAdd = car.copy(color = Color.GREEN)
        addCarUseCase.addCar(carAdd)
        editingFinished()
    }

    fun editCar(car: Car) {
        _selectedCar.value?.let {
            editCarUseCase.editCar(car)
            editingFinished()
        }
    }

    fun clearSelectedCar(){
        _selectedCar = MutableLiveData<Car>()
    }


    private fun editingFinished(){
        _readyToFinish.value = Unit
    }

}