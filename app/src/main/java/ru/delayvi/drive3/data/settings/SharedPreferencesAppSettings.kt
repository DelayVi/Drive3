package ru.delayvi.drive3.data.settings

import android.content.Context
import ru.delayvi.drive3.data.settings.AppSettings.Companion.NO_LOGGED_IN_USER_ID
import javax.inject.Inject

class SharedPreferencesAppSettings @Inject constructor(
    context: Context
) : AppSettings {

    private val sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    override fun getCurrentAccountId(): Long = sharedPreferences.getLong(PREF_CURRENT_USER_ID, NO_LOGGED_IN_USER_ID)

    override fun setCurrentAccountId(id: Long) {
        sharedPreferences.edit()
            .putLong(PREF_CURRENT_USER_ID, id)
            .apply()
    }

    companion object {
        private const val PREF_CURRENT_USER_ID = "currentUserId"
    }
}