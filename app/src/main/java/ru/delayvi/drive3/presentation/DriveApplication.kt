package ru.delayvi.drive3.presentation

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.parse.Parse
import com.parse.ParseUser
import com.parse.SignUpCallback
import ru.delayvi.drive3.R
import ru.delayvi.drive3.di.DaggerAppComponent

class DriveApplication: Application() {

    private val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .server(getString(R.string.back4app_server_url))
                .clientKey(getString(R.string.back4app_client_key))
                .build()
        )
        component.inject(this)


    }
}