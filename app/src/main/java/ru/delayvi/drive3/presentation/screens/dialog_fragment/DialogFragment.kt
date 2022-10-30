package ru.delayvi.drive3.presentation.screens.dialog_fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.delayvi.drive3.R

class DialogFragment : Fragment() {

    companion object {
        fun newInstance() = DialogFragment()
    }

    private lateinit var viewModel: DialogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DialogViewModel::class.java)
        // TODO: Use the ViewModel
    }

}