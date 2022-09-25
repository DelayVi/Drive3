package ru.delayvi.drive3.di

import dagger.Module
import dagger.Provides
import ru.delayvi.drive3.domain.usecases.*
import ru.delayvi.drive3.presentation.car_fragment.CarFragmentViewModel
import ru.delayvi.drive3.presentation.car_fragment.CarFragmentViewModelFactory
import ru.delayvi.drive3.presentation.main_fragment.MainViewModel
import ru.delayvi.drive3.presentation.main_fragment.MainViewModelFactory

@Module(includes = [AppBindsModule::class])
class AppModule {

    @Provides
    fun provideMainViewModel(
        getCarListUseCase: GetCarListUseCase,
        deleteCarUseCase: DeleteCarUseCase
    ): MainViewModel {
        return MainViewModel(
            getCarListUseCase,
            deleteCarUseCase
        )
    }

    @Provides
    fun provideMainViewModelFactory(
        getCarListUseCase: GetCarListUseCase,
        deleteCarUseCase: DeleteCarUseCase
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getCarListUseCase,
            deleteCarUseCase
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
}