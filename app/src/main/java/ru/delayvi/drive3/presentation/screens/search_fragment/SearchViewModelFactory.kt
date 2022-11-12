package ru.delayvi.drive3.presentation.screens.search_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.delayvi.drive3.domain.usecases.GetCarListUseCase
import ru.delayvi.drive3.domain.usecases.MakeFavoriteUseCase
import javax.inject.Inject

class SearchViewModelFactory @Inject constructor(
    private val getCarListUseCase: GetCarListUseCase,
    private val makeFavoriteUseCase: MakeFavoriteUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(
            getCarListUseCase, makeFavoriteUseCase
        ) as T
    }
}