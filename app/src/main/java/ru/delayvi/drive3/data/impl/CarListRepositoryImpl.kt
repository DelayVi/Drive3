package ru.delayvi.drive3.data.impl

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import ru.delayvi.drive3.data.database.AppDatabase
import ru.delayvi.drive3.data.database.CarDbModel
import ru.delayvi.drive3.data.database.CarMapper
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.repository.CarListRepository
import kotlin.random.Random

class CarListRepositoryImpl(
    application: Application
) : CarListRepository {

    private val carDao = AppDatabase.getInstance(application).carDao()
    private val mapper = CarMapper()



    override fun addCar(car: Car) {
        carDao.addCar(mapper.mapEntityToDbModel(car))
    }

    override fun deleteCar(car: Car) {
        carDao.deleteCar(car.id)
    }

    override fun editCar(car: Car) {
        carDao.addCar(mapper.mapEntityToDbModel(car))
    }

    override fun getCar(carId: Int): Car {
       var car = carDao.getCar(carId)
        return mapper.mapDbModelToEntity(car)
    }


    override fun getCarList(): LiveData<List<Car>> = Transformations.map(carDao.getCarList()){
        mapper.mapDbModelListToEntityList(it)
    }

//    override fun getCarList(): LiveData<List<Car>> = MediatorLiveData<List<Car>>().apply {
//        addSource(carDao.getCarList()){
//            postValue(mapper.mapDbModelListToEntityList(it))
//        }
//    }


//    override fun getCarList(): LiveData<List<Car>> {
//        var carList = MutableLiveData<List<Car>>()
//        carList.value = carDao.getCarList().value?.let { mapper.mapDbModelListToEntityList(it) }
//        return carList
//    }

}