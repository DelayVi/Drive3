package ru.delayvi.drive3.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.delayvi.drive3.domain.entity.Color

@Entity(tableName = "cars")
data class CarDbModel (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        var brand: String,
        var model: String,
        var price: String,
        var engine: String,
        var color: Color,
        var imageUri: String? = null,
        )