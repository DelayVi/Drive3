package ru.delayvi.drive3.di

import dagger.Module
import dagger.Provides
import ru.delayvi.drive3.domain.usecases.*
import ru.delayvi.drive3.presentation.screens.add_car_fragment.CarFragmentViewModel
import ru.delayvi.drive3.presentation.screens.add_car_fragment.CarFragmentViewModelFactory
import ru.delayvi.drive3.presentation.screens.favorite_fragment.FavoriteViewModel
import ru.delayvi.drive3.presentation.screens.favorite_fragment.FavoriteViewModelFactory
import ru.delayvi.drive3.presentation.screens.main_fragment.MainViewModel
import ru.delayvi.drive3.presentation.screens.main_fragment.MainViewModelFactory
import ru.delayvi.drive3.presentation.screens.show_car_fragment.ShowCarViewModel
import ru.delayvi.drive3.presentation.screens.show_car_fragment.ShowCarViewModelFactory


@Module(includes = [AppBindsModule::class])
class AppModule {

    @Provides
    fun provideMainViewModel(
        getCarListUseCase: GetCarListUseCase,
        makeFavoriteUseCase: MakeFavoriteUseCase
    ): MainViewModel {
        return MainViewModel(
            getCarListUseCase,
            makeFavoriteUseCase
        )
    }

    @Provides
    fun provideMainViewModelFactory(
        getCarListUseCase: GetCarListUseCase,
        makeFavoriteUseCase: MakeFavoriteUseCase
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getCarListUseCase,
            makeFavoriteUseCase
        )
    }

    @Provides
    fun provideFavoriteViewModel(
        getFavoriteCarListUseCase: GetFavoriteCarListUseCase,
        makeFavoriteUseCase: MakeFavoriteUseCase
    ): FavoriteViewModel {
        return FavoriteViewModel(
            getFavoriteCarListUseCase,
            makeFavoriteUseCase
        )
    }

    @Provides
    fun provideFavoriteViewModelFactory(
        getFavoriteCarListUseCase: GetFavoriteCarListUseCase,
        makeFavoriteUseCase: MakeFavoriteUseCase
    ): FavoriteViewModelFactory {
        return FavoriteViewModelFactory(
            getFavoriteCarListUseCase,
            makeFavoriteUseCase
        )
    }

    @Provides
    fun provideCarFragmentViewModel(
        addCarUseCase: AddCarUseCase,
        editCarUseCase: EditCarUseCase,
        getCarUseCase: GetCarUseCase
    ): CarFragmentViewModel {
        return CarFragmentViewModel(
            addCarUseCase,
            editCarUseCase,
            getCarUseCase
        )
    }

    @Provides
    fun provideCarFragmentViewModelFactory(
        addCarUseCase: AddCarUseCase,
        editCarUseCase: EditCarUseCase,
        getCarUseCase: GetCarUseCase
    ): CarFragmentViewModelFactory {
        return CarFragmentViewModelFactory(
            addCarUseCase,
            editCarUseCase,
            getCarUseCase
        )
    }

    @Provides
    fun provideShowCarFragmentViewModel(
        getCarUseCase: GetCarUseCase
    ): ShowCarViewModel {
        return ShowCarViewModel(
            getCarUseCase
        )
    }

    @Provides
    fun provideShowCarFragmentViewModelFactory(
        getCarUseCase: GetCarUseCase
    ): ShowCarViewModelFactory {
        return ShowCarViewModelFactory(
            getCarUseCase
        )
    }
}