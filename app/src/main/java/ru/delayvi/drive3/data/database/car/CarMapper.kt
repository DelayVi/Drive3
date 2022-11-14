package ru.delayvi.drive3.data.database.car

import ru.delayvi.drive3.domain.entity.cars.Car

class CarMapper {

    fun mapEntityToDbModel(car: Car) = with(car) {
        CarDbModel(id, brand, model, price, engine, fuel, ru.delayvi.drive3.domain.entity.cars.Color.WHITE, imageUri, year, description, isFavorite)
    }

    fun mapDbModelToEntity(carDbModel: CarDbModel) = with(carDbModel) {
        Car(brand, model, price, engine, fuel, color, imageUri, year, description, isFavorite, id)
    }

    fun mapEntityListToDbModelList(list: List<Car>) = list.map { mapEntityToDbModel(it) }

    fun mapDbModelListToEntityList(list: List<CarDbModel>) = list.map { mapDbModelToEntity(it)}
}