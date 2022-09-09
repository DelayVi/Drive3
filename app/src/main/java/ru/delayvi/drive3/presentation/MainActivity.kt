package ru.delayvi.drive3.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.delayvi.drive3.data.impl.CarListRepositoryImpl
import ru.delayvi.drive3.databinding.ActivityMainBinding
import ru.delayvi.drive3.presentation.recycler_view.CarListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private var carListAdapter = CarListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        viewModel.carList.observe(this){
            carListAdapter.submitList(it)
        }
        carListAdapter.onClickListener = {
            Toast.makeText(this, "Car ${it.brand} ${it.model}", Toast.LENGTH_SHORT).show()
        }
    }

    fun setupRecyclerView(){
        carListAdapter = CarListAdapter()
        binding.rvCar.adapter = carListAdapter
    }
}