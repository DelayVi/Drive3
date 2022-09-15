package ru.delayvi.drive3.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.delayvi.drive3.R
import ru.delayvi.drive3.databinding.ActivityMainBinding
import ru.delayvi.drive3.databinding.FragmentMainBinding
import ru.delayvi.drive3.presentation.car_fragment.CarFragment
import ru.delayvi.drive3.presentation.recycler_view.CarListAdapter

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private var carListAdapter = CarListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.carList.observe(viewLifecycleOwner) {
            carListAdapter.submitList(it)
        }


        binding.buttonAddCar.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToCarFragment2(false, null))
        }
        carListAdapter.onClickListener = {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToCarFragment2(true, it))
        }
    }

    private fun setupRecyclerView() {
        carListAdapter = CarListAdapter()
        binding.rvCar.adapter = carListAdapter
        swipeToRemove(binding.rvCar)
    }

    private fun swipeToRemove(recyclerView: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = carListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteCar(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}