package ru.delayvi.drive3.presentation.screens.search_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.delayvi.drive3.domain.usecases.cars.GetCarListUseCase
import ru.delayvi.drive3.domain.usecases.cars.MakeFavoriteUseCase
import ru.delayvi.drive3.domain.usecases.users.*
import javax.inject.Inject

class SearchViewModelFactory @Inject constructor(
    private val getCarListUseCase: GetCarListUseCase,
    private val makeFavoriteUseCase: MakeFavoriteUseCase,
    private val signUpUseCaseTest: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val getCurrentUserViewUseCase: GetCurrentUserViewUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(
            getCarListUseCase,
            makeFavoriteUseCase,
            signUpUseCaseTest,
            signInUseCase,
            getCurrentUserViewUseCase,
            logoutUseCase
        ) as T
    }
}