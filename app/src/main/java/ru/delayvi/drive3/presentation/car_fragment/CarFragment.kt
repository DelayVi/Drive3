package ru.delayvi.drive3.presentation.car_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.delayvi.drive3.databinding.FragmentCarBinding

class CarFragment : Fragment() {

    private lateinit var binding: FragmentCarBinding

    private var screenMode: String = MODE_UNKNOWN

    private val viewModel: CarFragmentViewModel by lazy {
        ViewModelProvider(this)[CarFragmentViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
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
        viewModel.selectedCar.observe(viewLifecycleOwner){
            binding.etName.setText(it.toString())
        }
        if (screenMode == MODE_EDIT) {
            viewModel.selectedCar.observe(viewLifecycleOwner) {
                binding.saveButton.setOnClickListener {
                    val brand = binding.etName.text.toString()
                    val model = binding.etCount.text.toString()
                    viewModel.editCar(brand, model)
                }
            }
        } else {
            binding.saveButton.setOnClickListener {
                val brand = binding.etName.text.toString()
                val model = binding.etCount.text.toString()
                viewModel.addCar(brand, model)
            }
        }


    }

    private fun parseArgs() {
        val arguments = requireArguments()
        if (!arguments.containsKey(EXTRA_SCREEN_MODE)) throw RuntimeException("Screen mode is absent")
        val mode = arguments.getString(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) throw RuntimeException("Unknown screen mode")
        screenMode = mode
    }

    companion object {

        private const val EXTRA_SCREEN_MODE = "extra_screen_mode"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddCar(): CarFragment {
            return CarFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_SCREEN_MODE, MODE_ADD)
                }
            }

        }

        fun newInstanceEditCar(): CarFragment {
            return CarFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_SCREEN_MODE, MODE_EDIT)
                }
            }

        }

    }
}