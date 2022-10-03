package ru.delayvi.drive3.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.delayvi.drive3.presentation.MainActivity
import ru.delayvi.drive3.presentation.car_fragment.CarFragment
import ru.delayvi.drive3.presentation.favorite_fragment.FavoriteFragment
import ru.delayvi.drive3.presentation.main_fragment.MainFragment

@Component(modules = [AppModule::class])
interface AppComponent {


    fun inject(activity: MainActivity)

    fun inject(fragment: CarFragment)

    fun inject(fragment: MainFragment)

    fun inject(fragment: FavoriteFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }


}