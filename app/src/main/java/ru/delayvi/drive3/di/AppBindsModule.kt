package ru.delayvi.drive3.di

import dagger.Binds
import dagger.Module
import ru.delayvi.drive3.data.impl.CarListRepositoryImpl
import ru.delayvi.drive3.data.impl.UserRepositoryImpl
import ru.delayvi.drive3.domain.repository.CarListRepository
import ru.delayvi.drive3.domain.repository.UserRepository

@Module
interface AppBindsModule {

    @Binds
    fun bindCarRepositoryImplToCarRepository(impl: CarListRepositoryImpl): CarListRepository

    @Binds
    fun bindUserRepositoryImplToUserRepository(impl: UserRepositoryImpl): UserRepository
}