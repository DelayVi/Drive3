package ru.delayvi.drive3.data.impl

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.delayvi.drive3.data.database.AppDatabase
import ru.delayvi.drive3.data.database.login.SavedUserDbModel
import ru.delayvi.drive3.data.database.login.UserDbModel
import ru.delayvi.drive3.domain.entity.cars.Car
import ru.delayvi.drive3.domain.entity.users.LoggedForm
import ru.delayvi.drive3.domain.entity.users.CurrentUserView
import ru.delayvi.drive3.domain.entity.users.User
import ru.delayvi.drive3.domain.entity.users.User.Companion.username
import ru.delayvi.drive3.domain.repository.UserRepository
import java.lang.Exception
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    application: Application
) : UserRepository {

    companion object {
        private const val GUEST_DISPLAY_NAME = "Guest"

        private var currentUser: User? = null

        private val currentUserView = MutableLiveData<CurrentUserView>()

        private val isAuthorized = MutableLiveData<Boolean>()
    }



    private val loginDao = AppDatabase.getInstance(application).loginDao()


    override suspend fun signIn(loggedForm: LoggedForm) {
        try {
            val user = loginDao.userSignIn(loggedForm.login, loggedForm.password).toUser()
            currentUser = user
            getCurrentUserView()
            isAuthorized()
            loginDao.saveLocalUser(SavedUserDbModel.fromLoggedForm(loggedForm))
            Log.d("MyLog", "success login pass")
        } catch (e: SQLiteConstraintException) {
            Log.d("MyLog", "unsuccess login pass")
        }
    }

    override suspend fun signUp(loggedForm: LoggedForm) {
        loginDao.userSignUp(UserDbModel.fromLoggedForm(loggedForm))
        signIn(loggedForm)
    }

    override suspend fun logout() {
        Log.d("MyLog", "call logout")
        loginDao.logout()
        currentUser = null
        getCurrentUserView()
        isAuthorized()
    }

    override fun getCurrentUserView(): LiveData<CurrentUserView> {
        currentUserView.value = CurrentUserView(currentUser?.username.toString())
        Log.d("MyLog", "getCurrentUserView in impl: ${currentUserView.value}")
        return currentUserView
    }

    override fun isAuthorized(): LiveData<Boolean>  {
        isAuthorized.value = currentUser != null
        Log.d("MyLog", "isAuthorized in impl: ${isAuthorized.value}")
        return isAuthorized
    }
}