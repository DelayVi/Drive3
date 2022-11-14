package ru.delayvi.drive3.data.impl

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ru.delayvi.drive3.data.database.AppDatabase
import ru.delayvi.drive3.data.database.car.CarMapper
import ru.delayvi.drive3.domain.entity.cars.Car
import ru.delayvi.drive3.domain.repository.CarListRepository
import javax.inject.Inject

class CarListRepositoryImpl @Inject constructor(
    application: Application
) : CarListRepository {

    private val carDao = AppDatabase.getInstance(application).carDao()
    private val mapper = CarMapper()



    override suspend fun addCar(car: Car) {
        carDao.addCar(mapper.mapEntityToDbModel(car))
    }

    override suspend fun deleteCar(car: Car) {
        carDao.deleteCar(car.id)
    }

    override suspend fun editCar(car: Car) {
        carDao.addCar(mapper.mapEntityToDbModel(car))
    }

    override suspend fun getCar(carId: Int): Car {
       var car = carDao.getCar(carId)
        return mapper.mapDbModelToEntity(car)
    }

    override suspend fun makeFavorite(carId: Int) {
        var car = carDao.getCar(carId)
        car.isFavorite = !car.isFavorite
        carDao.addCar(car)
    }


    override fun getCarList(): LiveData<List<Car>> = Transformations.map(carDao.getCarList()){
        mapper.mapDbModelListToEntityList(it)
    }

    override fun getFavoriteCarList(): LiveData<List<Car>> = Transformations.map(carDao.getFavoriteCarList()){
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