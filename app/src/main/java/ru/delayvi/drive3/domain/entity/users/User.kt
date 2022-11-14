package ru.delayvi.drive3.domain.entity.users

import java.util.*

data class User(
    val id: Int,
    var username: String,
) {
    companion object {
        val id: Int? = null
        val username: String? = null
    }

}