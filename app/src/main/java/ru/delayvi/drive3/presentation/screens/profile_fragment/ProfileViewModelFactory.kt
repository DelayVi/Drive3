package ru.delayvi.drive3.presentation.screens.profile_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.delayvi.drive3.domain.usecases.users.*
import javax.inject.Inject

class ProfileViewModelFactory @Inject constructor(
    private val isSignedInUseCase: IsSignedInUseCase,
    private val signUpUseCaseTest: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val getCurrentUserViewUseCase: GetCurrentUserViewUseCase,
    private val logoutUseCase: LogoutUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(
            isSignedInUseCase,
            signUpUseCaseTest,
            signInUseCase,
            getCurrentUserViewUseCase,
            logoutUseCase
        ) as T
    }
}