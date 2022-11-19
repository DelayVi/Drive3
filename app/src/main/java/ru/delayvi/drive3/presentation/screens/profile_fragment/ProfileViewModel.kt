package ru.delayvi.drive3.presentation.screens.profile_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.delayvi.drive3.domain.entity.users.CurrentUserView
import ru.delayvi.drive3.domain.entity.users.LoggedForm
import ru.delayvi.drive3.domain.usecases.users.*

class ProfileViewModel(
    private val isAuthorizedUseCase: IsAuthorizedUseCase,
    private val signUpUseCaseTest: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val getCurrentUserViewUseCase: GetCurrentUserViewUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {


    private val _isAuthorized = isAuthorizedUseCase()
    val isAuthorized = _isAuthorized

    private val _currentUserView = getCurrentUserViewUseCase()
    val currentUserView = _currentUserView

    fun logout() = viewModelScope.launch {
        logoutUseCase()
    }

    fun signUpTest(loggedForm: LoggedForm) = viewModelScope.launch {
        signUpUseCaseTest(loggedForm)
    }

    fun signIn(login: String, password: String) = viewModelScope.launch {
        signInUseCase(LoggedForm(login, password))
    }

}