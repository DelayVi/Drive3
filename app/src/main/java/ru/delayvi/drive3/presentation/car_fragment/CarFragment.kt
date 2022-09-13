package ru.delayvi.drive3.presentation.car_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import ru.delayvi.drive3.R
import ru.delayvi.drive3.databinding.FragmentCarBinding
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.entity.Color

class CarFragment : Fragment() {

    private lateinit var binding: FragmentCarBinding
    private val args by navArgs<CarFragmentArgs>()
    private val viewModel: CarFragmentViewModel by lazy {
        ViewModelProvider(this)[CarFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.readyToFinish.observe(viewLifecycleOwner) {
            requireActivity().onBackPressed()
        }
        if (args.screenModeEdit) {
            binding.viewModel = viewModel
            binding.lifecycleOwner = viewLifecycleOwner
            viewModel.selectedCar.observe(viewLifecycleOwner) {
                entryParams(it)
                binding.saveButton.setOnClickListener {
                    viewModel.editCar(saveParamsCar())
                }
            }
        } else {
            binding.saveButton.setOnClickListener {
                viewModel.addCar(saveParamsCar())
            }
        }
    }

    private fun saveParamsCar(): Car {
        return with(binding) {
            Car(
                etBrand.text.toString(),
                etModel.text.toString(),
                etPrice.text.toString(),
                etEngine.text.toString(),
                Color.WHITE
            )
        }
    }

    private fun entryParams(car: Car) {
        Picasso.get()
            .load(car.imageUri)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.ivCar)
    }
}