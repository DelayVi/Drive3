package ru.delayvi.drive3.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    var brand: String,
    var model: String,
    var price: String,
    var engine: String,
    var fuel: Fuel = Fuel.DIESEL,
    var color: Color = Color.YELLOW,
    var imageUri: String? = null,
    var year: Int = 2022,
    var description: String = "",
    var isFavorite: Boolean = false,
    var id: Int = UNDEFINED_ID,
) : Parcelable {
    companion object {
        const val UNDEFINED_ID = 0
    }
}