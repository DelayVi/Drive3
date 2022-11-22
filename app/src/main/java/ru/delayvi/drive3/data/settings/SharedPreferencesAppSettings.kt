package ru.delayvi.drive3.data.settings

import android.app.Application
import android.content.Context
import ru.delayvi.drive3.data.settings.AppSettings.Companion.NO_LOGGED_IN_DISPLAY_NAME
import ru.delayvi.drive3.data.settings.AppSettings.Companion.NO_LOGGED_IN_USER_ID
import ru.delayvi.drive3.domain.entity.users.User
import ru.delayvi.drive3.domain.entity.users.User.Companion.id
import javax.inject.Inject

class SharedPreferencesAppSettings @Inject constructor(
    application: Application
): AppSettings {

    private val sharedPreferences = application.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun getCurrentAccountId(): Long = sharedPreferences.getLong(PREF_CURRENT_ACCOUNT_ID, NO_LOGGED_IN_USER_ID)


    fun setCurrentAccountId(id: Long) {
        sharedPreferences.edit()
            .putLong(PREF_CURRENT_ACCOUNT_ID, id)
            .apply()
    }

    fun getCurrentUsername(): String = sharedPreferences.getString(
        PREF_CURRENT_ACCOUNT_DISPLAY_NAME,
        NO_LOGGED_IN_DISPLAY_NAME) ?: NO_LOGGED_IN_DISPLAY_NAME

    fun setCurrentUsername(displayName: String) {
        sharedPreferences.edit()
            .putString(PREF_CURRENT_ACCOUNT_DISPLAY_NAME, displayName)
            .apply()
    }

    companion object {
        private const val PREF_CURRENT_ACCOUNT_ID = "currentAccountId"

        private const val PREF_CURRENT_ACCOUNT_DISPLAY_NAME = "currentDisplayName"
    }

    override fun getCurrentUser(): User {
        val id = getCurrentAccountId()
        val username = getCurrentUsername()
        return User(id, username)
    }

    override fun setCurrentUser(user: User) {
        setCurrentAccountId(user.id)
        setCurrentUsername(user.username)
    }
}