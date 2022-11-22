package ru.delayvi.drive3.presentation.screens.profile_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.delayvi.drive3.domain.entity.users.LoggedForm
import ru.delayvi.drive3.domain.usecases.users.*

class ProfileViewModel(
    private val isSignedInUseCase: IsSignedInUseCase,
    private val signUpUseCaseTest: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val getCurrentUserViewUseCase: GetCurrentUserViewUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {


    private val _isSignedIn = isSignedInUseCase()
    val isSignedIn = _isSignedIn


    fun logout() = viewModelScope.launch {
        logoutUseCase()
    }

    fun getCurrentUserView() = getCurrentUserViewUseCase()


    fun signUpTest(loggedForm: LoggedForm) = viewModelScope.launch {
        signUpUseCaseTest(loggedForm)
    }

    fun signIn(login: String, password: String) = viewModelScope.launch {
        signInUseCase(LoggedForm(login, password))
    }

}