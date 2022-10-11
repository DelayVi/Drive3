package ru.delayvi.drive3.presentation.screens.favorite_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.delayvi.drive3.domain.usecases.GetFavoriteCarListUseCase
import ru.delayvi.drive3.domain.usecases.MakeFavoriteUseCase
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(
    private val getFavoriteCarListUseCase: GetFavoriteCarListUseCase,
    private val makeFavoriteUseCase: MakeFavoriteUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteViewModel(getFavoriteCarListUseCase, makeFavoriteUseCase) as T
    }
}