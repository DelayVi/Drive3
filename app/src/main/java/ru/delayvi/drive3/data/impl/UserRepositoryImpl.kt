package ru.delayvi.drive3.data.impl

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.delayvi.drive3.data.database.AppDatabase
import ru.delayvi.drive3.data.database.login.UserDbModel
import ru.delayvi.drive3.data.settings.AppSettings
import ru.delayvi.drive3.data.settings.AppSettings.Companion.NO_LOGGED_IN_DISPLAY_NAME
import ru.delayvi.drive3.data.settings.AppSettings.Companion.NO_LOGGED_IN_USER_ID
import ru.delayvi.drive3.domain.entity.users.LoggedForm
import ru.delayvi.drive3.domain.entity.users.User
import ru.delayvi.drive3.domain.repository.UserRepository
import java.lang.reflect.Field
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    application: Application,
    private val appSettings: AppSettings
) : UserRepository {

    companion object {
        val isSignedIn = MutableLiveData<Boolean>()
    }

    private val loginDao = AppDatabase.getInstance(application).loginDao()

    override suspend fun signIn(loggedForm: LoggedForm) {
        try {
            val user = loginDao.userSignIn(loggedForm.login, loggedForm.password).toUser()
            appSettings.setCurrentUser(user)
            getCurrentUserView()
            isSignedIn()
        } catch (e: SQLiteConstraintException) {
            Log.d("MyLog", "unsuccess login pass")
        }
    }

    override fun isSignedIn(): LiveData<Boolean> {
        isSignedIn.value = appSettings.getCurrentUser().id != NO_LOGGED_IN_USER_ID
        Log.d("MyLog","isSignedIn in impl")
        return isSignedIn
    }


    override suspend fun signUp(loggedForm: LoggedForm) {
        loginDao.userSignUp(UserDbModel.fromLoggedForm(loggedForm))
        signIn(loggedForm)
    }

    override suspend fun logout() {
        appSettings.setCurrentUser(User(id = NO_LOGGED_IN_USER_ID, username = NO_LOGGED_IN_DISPLAY_NAME))
        isSignedIn()
    }

    override fun getCurrentUserView(): String {
        return appSettings.getCurrentUser().username
    }

}