package ru.delayvi.drive3.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.delayvi.drive3.R
import ru.delayvi.drive3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bNav.setupWithNavController(
            (supportFragmentManager.findFragmentById(R.id.fragmentCar) as NavHostFragment).navController
        )
    }

}