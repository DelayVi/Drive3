package ru.delayvi.drive3.presentation.screens.search_fragment

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.usecases.GetCarListUseCase
import ru.delayvi.drive3.domain.usecases.MakeFavoriteUseCase

class SearchViewModel(
    private val getCarListUseCase: GetCarListUseCase,
    private val makeFavoriteUseCase: MakeFavoriteUseCase
) : ViewModel() {

    private val _carList = getCarListUseCase()
    val carList: LiveData<List<Car>> = _carList

    fun makeFavorite(carId: Int) {
        viewModelScope.launch {
            makeFavoriteUseCase(carId)
            val database = Firebase.database("https://drive3-ade49-default-rtdb.europe-west1.firebasedatabase.app")
            val myRef = database.getReference("message")
            Log.d("MyLog", myRef.toString())
            myRef.setValue("Hello, World!")

        }
    }

}