package ru.delayvi.drive3.presentation

import android.app.Application
import ru.delayvi.drive3.di.DaggerAppComponent

class MainApplication: Application() {

    private val appComponent by lazy{
        DaggerAppComponent.create()
    }

    override fun onCreate() {
        super.onCreate()
    }
}