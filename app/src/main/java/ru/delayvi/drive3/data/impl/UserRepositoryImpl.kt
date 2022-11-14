package ru.delayvi.drive3.data.impl

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.delayvi.drive3.data.database.AppDatabase
import ru.delayvi.drive3.data.database.login.SavedUserDbModel
import ru.delayvi.drive3.data.database.login.UserDbModel
import ru.delayvi.drive3.domain.entity.users.LoggedForm
import ru.delayvi.drive3.domain.entity.users.CurrentUserView
import ru.delayvi.drive3.domain.entity.users.User
import ru.delayvi.drive3.domain.repository.UserRepository
import java.lang.Exception
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    application: Application
) : UserRepository {

    companion object {
        private const val GUEST_DISPLAY_NAME = "Guest"
    }

    private var currentUser: User? = null

    private val loginDao = AppDatabase.getInstance(application).loginDao()


    override suspend fun signIn(loggedForm: LoggedForm) {
        try {
            val user = loginDao.userSignIn(loggedForm.login, loggedForm.password).toUser()
            currentUser = user
            loginDao.saveLocalUser(SavedUserDbModel.fromLoggedForm(loggedForm))
        } catch (e: Exception) {
        }
    }

    override suspend fun signUp(loggedForm: LoggedForm) {
        loginDao.userSignUp(UserDbModel.fromLoggedForm(loggedForm))
        signIn(loggedForm)
    }

    override suspend fun logout() {
        loginDao.logout()
        currentUser = null
    }

    override fun getCurrentUserView(): LiveData<CurrentUserView> {
        val displayName =  currentUser?.username ?: GUEST_DISPLAY_NAME
        println("displayName in Impl $displayName")
        val currentUserView = CurrentUserView(displayName = displayName)
        val liveData = MutableLiveData<CurrentUserView>()
        liveData.setValue(currentUserView)
        return liveData
    }

    override fun isAuthorized(): LiveData<Boolean>  {
        val liveData = MutableLiveData<Boolean>()
        liveData.setValue(currentUser != null)
        return liveData
    }
}