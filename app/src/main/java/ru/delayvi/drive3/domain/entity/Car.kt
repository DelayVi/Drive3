package ru.delayvi.drive3.domain.entity

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    var brand: String,
    var model: String,
    var price: String,
    var engine: String,
    var color: Color,
    var imageUri: String? = null,
    var id: Int = UNDEFINED_ID,
) : Parcelable {
    companion object {
        const val UNDEFINED_ID = -1
    }
}