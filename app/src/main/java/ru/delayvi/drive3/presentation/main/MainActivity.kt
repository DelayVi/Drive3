package ru.delayvi.drive3.presentation.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ru.delayvi.drive3.R
import ru.delayvi.drive3.databinding.ActivityMainBinding
import ru.delayvi.drive3.presentation.car_fragment.CarFragment
import ru.delayvi.drive3.presentation.recycler_view.CarListAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}