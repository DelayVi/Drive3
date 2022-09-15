package ru.delayvi.drive3.data.database

import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.entity.Color

class CarMapper {

    fun mapEntityToDbModel(car: Car) = with(car) {
        CarDbModel(id, brand, model, price, engine, Color.WHITE, imageUri)
    }

    fun mapDbModelToEntity(carDbModel: CarDbModel) = with(carDbModel) {
        Car(brand, model, price, engine, color, imageUri, id)
    }

    fun mapEntityListToDbModelList(list: List<Car>) = list.map { mapEntityToDbModel(it) }

    fun mapDbModelListToEntityList(list: List<CarDbModel>) = list.map { mapDbModelToEntity(it)}
}