package ru.delayvi.drive3.presentation.screens.messages_fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.delayvi.drive3.R

class MessengerFragment : Fragment() {

    companion object {
        fun newInstance() = MessengerFragment()
    }

    private lateinit var viewModel: MessengerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MessengerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}