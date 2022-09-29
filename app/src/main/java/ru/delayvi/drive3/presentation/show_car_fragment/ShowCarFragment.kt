package ru.delayvi.drive3.presentation.show_car_fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.delayvi.drive3.R

class ShowCarFragment : Fragment() {

    companion object {
        fun newInstance() = ShowCarFragment()
    }

    private lateinit var viewModel: ShowCarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_car, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShowCarViewModel::class.java)
        // TODO: Use the ViewModel
    }

}