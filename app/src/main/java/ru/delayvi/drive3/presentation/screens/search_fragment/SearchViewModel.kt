package ru.delayvi.drive3.presentation.screens.search_fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import ru.delayvi.drive3.domain.entity.cars.Car
import ru.delayvi.drive3.domain.entity.users.CurrentUserView
import ru.delayvi.drive3.domain.entity.users.LoggedForm
import ru.delayvi.drive3.domain.usecases.cars.GetCarListUseCase
import ru.delayvi.drive3.domain.usecases.cars.MakeFavoriteUseCase
import ru.delayvi.drive3.domain.usecases.users.*
import kotlin.math.log

class SearchViewModel(
    private val getCarListUseCase: GetCarListUseCase,
    private val makeFavoriteUseCase: MakeFavoriteUseCase,
    private val isAuthorizedUseCase: IsAuthorizedUseCase,
    private val signUpUseCaseTest: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val getCurrentUserViewUseCase: GetCurrentUserViewUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _carList = getCarListUseCase()
    val carList: LiveData<List<Car>> = _carList

    private val _isAuthorized = isAuthorizedUseCase()
    val isAuthorized: LiveData<Boolean> = _isAuthorized

    private val _currentUserView = getCurrentUserViewUseCase()
    val currentUserView: LiveData<CurrentUserView> = _currentUserView

    fun makeFavorite(carId: Int) {
        viewModelScope.launch {
            makeFavoriteUseCase(carId)
            val database = Firebase.database("https://drive3-ade49-default-rtdb.europe-west1.firebasedatabase.app")
            val myRef = database.getReference("message")
            Log.d("MyLog", myRef.toString())
            myRef.setValue("Hello, World!")

        }
    }

    fun logout() = viewModelScope.launch {
        logoutUseCase()
    }

    fun signUpTest(loggedForm: LoggedForm) = viewModelScope.launch {
        signUpUseCaseTest(loggedForm)
    }

    fun signIn(loggedForm: LoggedForm) = viewModelScope.launch {
        signInUseCase(loggedForm)
        currentUserView
    }

}