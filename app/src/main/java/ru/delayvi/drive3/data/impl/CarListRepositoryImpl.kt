package ru.delayvi.drive3.data.impl

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.parcelize.Parcelize
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.repository.CarListRepository
import java.lang.RuntimeException
import kotlin.random.Random

object CarListRepositoryImpl : CarListRepository {

    private const val AUTO_INCREMENT_ID = 0


    private val carList = sortedSetOf<Car>({ p0, p1 -> p0.id.compareTo(p1.id) })
    private val carListLiveData = MutableLiveData<List<Car>>()

    private var autoIncrementId = AUTO_INCREMENT_ID

    init {
        for (i in 0 until 30){
            val car = Car(i,"Brand $i", "Model $i", "${Random.nextInt(100, 5000)}000", "${Random.nextDouble(0.8,6.0)}", randomColor() )
            addCar(car)
        }
    }

    private fun randomColor(): ru.delayvi.drive3.domain.entity.Color {
        return when(Random.nextInt(0,5)){
            0 -> ru.delayvi.drive3.domain.entity.Color.BLACK
            1-> ru.delayvi.drive3.domain.entity.Color.BLUE
            2-> ru.delayvi.drive3.domain.entity.Color.RED
            3-> ru.delayvi.drive3.domain.entity.Color.WHITE
            4-> ru.delayvi.drive3.domain.entity.Color.GREEN
            else -> {
                throw RuntimeException("Unknown color")
            }
        }
    }


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