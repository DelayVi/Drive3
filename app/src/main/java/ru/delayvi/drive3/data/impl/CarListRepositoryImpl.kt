package ru.delayvi.drive3.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.parcelize.Parcelize
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.repository.CarListRepository
import java.lang.RuntimeException

object CarListRepositoryImpl : CarListRepository {

    private const val AUTO_INCREMENT_ID = 0


    private val carList = sortedSetOf<Car>({ p0, p1 -> p0.id.compareTo(p1.id) })
    private val carListLiveData = MutableLiveData<List<Car>>()

    private var autoIncrementId = AUTO_INCREMENT_ID




    override fun addCar(car: Car) {
        if (car.id == Car.UNDEFINED_ID) car.id = autoIncrementId++
        carList.add(car)
        updateLiveData()
    }

    override fun deleteCar(car: Car) {
        carList.remove(car)
        updateLiveData()
    }

    override fun editCar(car: Car) {
        val oldCar = getCar(car.id)
        deleteCar(oldCar)
        addCar(car)
        updateLiveData()
    }

    override fun getCarList(): LiveData<List<Car>> {
        updateLiveData()
        return carListLiveData
    }

    override fun getCar(carId: Int): Car {
        updateLiveData()
        return carList.find { it.id == carId } ?: throw RuntimeException("Car with id $carId not found")
    }

    private fun updateLiveData() {
        carListLiveData.postValue(carList.toList())
    }


}