package ru.delayvi.drive3.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.delayvi.drive3.domain.entity.Car

@Dao
interface CarDao {

    @Query("SELECT * FROM cars")
    fun getCarList(): LiveData<List<CarDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCar(carDbModel: CarDbModel)

    @Query ("DELETE FROM cars WHERE id =:carId")
    fun deleteCar(carId: Int)

    @Query("SELECT * FROM cars where id =:carId LIMIT 1")
    fun getCar(carId: Int): CarDbModel
}