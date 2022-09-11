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

    private val _selectedCar = getSelectedCar.getSelectedCar()
    val selectedCar: LiveData<Car> = _selectedCar

    fun addCar(brand: String, model: String){
        val carAdd = Car(
            brand,
            model,
            "10000rubasov",
            "4.4twinTurbo",
            Color.GREEN
        )
        addCarUseCase.addCar(carAdd)
        editingFinished()
    }

    fun editCar(brand: String, model: String) {
        _selectedCar.value?.let {
            val carCopy = it.copy(brand = brand, model = model)
            editCarUseCase.editCar(carCopy)
            editingFinished()
        }
    }


    private fun editingFinished(){
        _readyToFinish.value = Unit
    }

}