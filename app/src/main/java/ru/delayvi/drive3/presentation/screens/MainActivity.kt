package ru.delayvi.drive3.presentation.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.delayvi.drive3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}