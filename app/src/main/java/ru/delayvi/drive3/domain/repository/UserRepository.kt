package ru.delayvi.drive3.domain.repository

import androidx.lifecycle.LiveData
import ru.delayvi.drive3.domain.entity.users.LoggedForm
import ru.delayvi.drive3.domain.entity.users.CurrentUserView

interface UserRepository {

    suspend fun signUp(loggedForm: LoggedForm)

    suspend fun signIn(loggedForm: LoggedForm)

    suspend fun logout()

    fun getCurrentUserView(): LiveData<CurrentUserView>

    fun isAuthorized(): LiveData<Boolean>
}