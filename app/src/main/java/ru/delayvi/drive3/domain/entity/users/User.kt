package ru.delayvi.drive3.domain.entity.users

import java.util.*

data class User(
    val id: Long,
    var username: String,
) {
    companion object {
        val id: Long? = null
        val username: String? = null
    }

}