package ru.delayvi.drive3.presentation.screens.search_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.delayvi.drive3.domain.entity.users.LoggedForm
import ru.delayvi.drive3.domain.usecases.cars.GetCarListUseCase
import ru.delayvi.drive3.domain.usecases.cars.MakeFavoriteUseCase
import ru.delayvi.drive3.domain.usecases.users.*

class SearchViewModel(
    private val getCarListUseCase: GetCarListUseCase,
    private val makeFavoriteUseCase: MakeFavoriteUseCase,
    private val signUpUseCaseTest: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val getCurrentUserViewUseCase: GetCurrentUserViewUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _carList = getCarListUseCase()
    val carList = _carList

//    private val _currentUserView = getCurrentUserViewUseCase()
//    //val currentUserView: LiveData<CurrentUserView> = _currentUserView

    fun makeFavorite(carId: Int) {
        viewModelScope.launch {
            makeFavoriteUseCase(carId)
        }
    }

    fun logout() = viewModelScope.launch {
        logoutUseCase
    }

    fun signUpTest(loggedForm: LoggedForm) = viewModelScope.launch {
        signUpUseCaseTest(loggedForm)
    }

    fun signIn(login: String, password: String) = viewModelScope.launch {
        signInUseCase(LoggedForm(login, password))
    }

}