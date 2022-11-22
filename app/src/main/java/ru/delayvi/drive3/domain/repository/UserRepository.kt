package ru.delayvi.drive3.domain.repository

import androidx.lifecycle.LiveData
import ru.delayvi.drive3.domain.entity.users.LoggedForm

interface UserRepository {

    fun isSignedIn(): LiveData<Boolean>

    suspend fun signUp(loggedForm: LoggedForm)

    suspend fun signIn(loggedForm: LoggedForm)

    suspend fun logout()

    fun getCurrentUserView(): String
}