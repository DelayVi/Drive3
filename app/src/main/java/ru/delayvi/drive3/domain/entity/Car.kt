package ru.delayvi.drive3.domain.entity

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    var id: Int = UNDEFINED_ID,
    var name: String,
    var model: String,
    var price: Double,
    var engine: Double,
    var color: String,
    var imageUri: Uri
) : Parcelable {
    companion object {
        const val UNDEFINED_ID = -1
    }
}