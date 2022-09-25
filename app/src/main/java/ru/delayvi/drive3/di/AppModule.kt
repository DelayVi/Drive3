package ru.delayvi.drive3.di

import dagger.Module
import dagger.Provides
import ru.delayvi.drive3.domain.usecases.AddCarUseCase
import ru.delayvi.drive3.domain.usecases.EditCarUseCase
import ru.delayvi.drive3.domain.usecases.GetCarUseCase
import ru.delayvi.drive3.presentation.car_fragment.CarFragmentViewModel
import ru.delayvi.drive3.presentation.main_fragment.MainViewModel

@Module(includes = [AppBindsModule::class])
class AppModule {

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
}