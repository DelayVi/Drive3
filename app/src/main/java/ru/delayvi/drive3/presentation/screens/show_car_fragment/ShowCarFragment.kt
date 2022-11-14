package ru.delayvi.drive3.presentation.screens.show_car_fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import ru.delayvi.drive3.R
import ru.delayvi.drive3.databinding.FragmentShowCarBinding
import ru.delayvi.drive3.di.DaggerAppComponent
import javax.inject.Inject

class ShowCarFragment : Fragment() {

    private val args by navArgs<ShowCarFragmentArgs>()

    private var _binding: FragmentShowCarBinding? = null
    private val binding: FragmentShowCarBinding
    get() = _binding ?: throw RuntimeException("Fragment Show Car is null")

    @Inject
    lateinit var viewModelFactory: ShowCarViewModelFactory

    private val viewModel: ShowCarViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ShowCarViewModel::class.java]
    }
    private val appComponent by lazy {
        DaggerAppComponent.factory().create(requireActivity().application)
    }

    override fun onAttach(context: Context) {
        appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowCarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCar(args.carId)

        binding.tvTakeCredit.setOnClickListener{
            toast("Бери кредит")
        }
        binding.buttonSendMessage.setOnClickListener {
            binding.scrollViewShowCar.scrollTo(0, 100)
            toast("Написать")
        }
        viewModel.carItem.observe(viewLifecycleOwner){
            binding.showCarItem = it
            placePhoto(it.imageUri.toString())
        }

        binding.linearLayoutMessage.setOnClickListener {
            toast("Нажали на сообщение")
        }

    }


    private fun toast(str: String){
        Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show()
    }

    private fun placePhoto(uri: String) {
        Picasso.get().load(uri).placeholder(R.drawable.ic_launcher_foreground).into(binding.ivCar)
    }


}