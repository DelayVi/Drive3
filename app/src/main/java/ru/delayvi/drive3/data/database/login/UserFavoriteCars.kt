package ru.delayvi.drive3.data.database.login

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_favorite_cars",
    indices = [
    Index("username", unique = true)
    ]
)
data class UserFavoriteCars(
    @PrimaryKey
    private val username: String,
    private val carId: Int,
    @ColumnInfo(name = "is_favorite")
    private val isFavorite: Boolean = false
)
