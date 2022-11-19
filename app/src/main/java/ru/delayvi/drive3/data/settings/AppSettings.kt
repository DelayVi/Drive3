package ru.delayvi.drive3.data.settings

interface AppSettings {

    fun getCurrentAccountId(): Long

    fun setCurrentAccountId(id: Long)

    companion object {
        const val NO_LOGGED_IN_USER_ID = -1L
    }
}