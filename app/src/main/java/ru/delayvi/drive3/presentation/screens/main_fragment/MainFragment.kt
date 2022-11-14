package ru.delayvi.drive3.presentation.screens.main_fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.delayvi.drive3.databinding.FragmentMainBinding
import ru.delayvi.drive3.di.DaggerAppComponent
import ru.delayvi.drive3.presentation.recycler_view.CarListAdapter
import javax.inject.Inject

class MainFragment() : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private val appComponent by lazy {
        DaggerAppComponent.factory().create(requireActivity().application)
    }

    private var carListAdapter = CarListAdapter()

    override fun onAttach(context: Context) {
        appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToSearchFragment())
        viewModel.carList.observe(viewLifecycleOwner) {
            carListAdapter.submitList(it)
        }
        carListAdapter.onClickListener = {
            launchShowCarFragment(it.id)
        }

        carListAdapter.onFavoriteStarClickListener = {
            viewModel.makeFavorite(it.id)
        }
    }

    private fun launchShowCarFragment(carID: Int) {
        //   findNavController().navigate(MainFragmentDirections.actionMainFragmentToShowCarFragment(carID))
    }

    private fun setupRecyclerView() {
        carListAdapter = CarListAdapter()
        // binding.rvCar.adapter = carListAdapter
    }


}