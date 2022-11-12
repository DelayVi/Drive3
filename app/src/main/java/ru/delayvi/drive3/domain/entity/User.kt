package ru.delayvi.drive3.domain.entity

data class User(
    val id: Int,
    var username: String,
    var favoriteCars: MutableList<Int>
) {

}