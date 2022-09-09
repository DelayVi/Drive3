package ru.delayvi.drive3.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.delayvi.drive3.R
import ru.delayvi.drive3.databinding.ActivityMainBinding
import ru.delayvi.drive3.databinding.FragmentCarBinding
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.presentation.recycler_view.CarListAdapter

class CarFragment : Fragment() {

    private lateinit var binding: FragmentCarBinding

    private var screenMode: String = MODE_UNKNOWN
    private var carId: Int = Car.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvCarName
    }

    companion object {

        private const val EXTRA_SCREEN_MODE = "extra_screen_mode"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddCar():CarFragment {
            return CarFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_SCREEN_MODE, MODE_ADD)
                }
            }

        }

    }
}