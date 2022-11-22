package ru.delayvi.drive3.data.settings

import ru.delayvi.drive3.domain.entity.users.User

interface AppSettings {

    fun getCurrentUser(): User

    fun setCurrentUser(user: User)

    companion object {

        const val NO_LOGGED_IN_USER_ID = -1L

        const val NO_LOGGED_IN_DISPLAY_NAME = "Guest"
    }
}