package ru.delayvi.drive3.data.database.car

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.delayvi.drive3.domain.entity.cars.Color
import ru.delayvi.drive3.domain.entity.cars.Fuel

@Entity(tableName = "cars")
data class CarDbModel (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        var brand: String,
        var model: String,
        var price: String,
        var engine: String,
        var fuel: Fuel,
        var color: Color,
        var imageUri: String? = null,
        var year: Int,
        var description: String = "",
        var isFavorite: Boolean = false
        )