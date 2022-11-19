package ru.delayvi.drive3.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.delayvi.drive3.presentation.DriveApplication
import ru.delayvi.drive3.presentation.screens.MainActivity
import ru.delayvi.drive3.presentation.screens.add_car_fragment.CarFragment
import ru.delayvi.drive3.presentation.screens.favorite_fragment.FavoriteFragment
import ru.delayvi.drive3.presentation.screens.main_fragment.MainFragment
import ru.delayvi.drive3.presentation.screens.profile_fragment.ProfileFragment
import ru.delayvi.drive3.presentation.screens.search_fragment.SearchFragment
import ru.delayvi.drive3.presentation.screens.search_fragment.SearchViewModel
import ru.delayvi.drive3.presentation.screens.show_car_fragment.ShowCarFragment

@Component(modules = [AppModule::class])
interface AppComponent {


    fun inject(application: DriveApplication)

    fun inject(activity: MainActivity)

    fun inject(fragment: CarFragment)

    fun inject(fragment: MainFragment)

    fun inject(fragment: FavoriteFragment)

    fun inject(fragment: ShowCarFragment)

    fun inject(fragment: SearchFragment)

    fun inject(fragment: ProfileFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }


}