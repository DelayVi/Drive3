package ru.delayvi.drive3.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToCarFragment2(false))
        }
        carListAdapter.onClickListener = {
            viewModel.selectCar(it)
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToCarFragment2(true))
        }
    }
    private fun setupRecyclerView() {
        carListAdapter = CarListAdapter()
        binding.rvCar.adapter = carListAdapter
    }

    private fun launchFragment(fragment: CarFragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_car, fragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {


        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}