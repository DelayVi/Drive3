package ru.delayvi.drive3.di

import dagger.Binds
import dagger.Module
import ru.delayvi.drive3.data.impl.CarListRepositoryImpl
import ru.delayvi.drive3.domain.repository.CarListRepository

@Module
interface AppBindsModule {

    @Binds
    fun bindRepositoryImplToRepository(impl: CarListRepositoryImpl): CarListRepository
}