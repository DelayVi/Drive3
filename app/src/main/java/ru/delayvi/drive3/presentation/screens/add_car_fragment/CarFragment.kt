package ru.delayvi.drive3.presentation.screens.add_car_fragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import ru.delayvi.drive3.R
import ru.delayvi.drive3.databinding.FragmentAddCarBinding
import ru.delayvi.drive3.di.DaggerAppComponent
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.entity.Color
import ru.delayvi.drive3.domain.entity.Fuel
import java.io.ByteArrayOutputStream
import javax.inject.Inject


class CarFragment : Fragment() {

    private lateinit var binding: FragmentAddCarBinding
    //private val args by navArgs<CarFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: CarFragmentViewModelFactory

    private var carImageUri: String? = null

    private val viewModel: CarFragmentViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CarFragmentViewModel::class.java]
    }

    private val appComponent by lazy {
        DaggerAppComponent.factory().create(requireActivity().application)
    }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                carImageUri = uri.toString()
                placePhoto(uri.toString())
            }
        }


    companion object {
        fun getInstance(): CarFragment {
            return CarFragment()
        }
        const val ID_SCREEN_MODE_ADD = -1
        const val BASE_IMAGE_URL = "https://cdn.imagin.studio/getimage?customer=rudenistumanovcompany&"
        const val UNKNOWN_CAR_IMAGE =
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcS0d1kv5Md3QWJtMPv3pATm142PTng_lFyp4C_duADFiW4UlIRs"
    }

    override fun onAttach(context: Context) {
        appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.readyToFinish.observe(viewLifecycleOwner) {
            requireActivity().onBackPressed()
        }
//        if (carID != ID_SCREEN_MODE_ADD) {
//           // entryParams(args.carID)
//            binding.lifecycleOwner = viewLifecycleOwner
//            binding.saveButton.setOnClickListener {
//                viewModel.editCar(saveParamsCar())
//            }
//        } else {
            placePhoto(UNKNOWN_CAR_IMAGE)
            binding.saveButton.setOnClickListener {
                viewModel.addCar(saveParamsCar())
            }

      //  }
        binding.ivCar.setOnClickListener {
            getContent.launch("image/*")
        }

    }


    private fun saveParamsCar(): Car {
        with(binding) {
            val name = etBrand.text.toString()
            val model = etModel.text.toString()
            val price = etPrice.text.toString()
            val engine = etEngine.text.toString()
            if (carImageUri == null) carImageUri = "${BASE_IMAGE_URL}make=$name&modelFamily=$model"
            Log.d("MyLog", carImageUri.toString())
            return Car(
                name, model, price, engine, Fuel.DIESEL, Color.WHITE, carImageUri, 2021
            )
        }
    }

    private fun placePhoto(uri: String) {
        Picasso.get().load(uri).placeholder(R.drawable.ic_launcher_foreground).into(binding.ivCar)
    }

    private fun uploadImage() {
            val bitmap = (binding.ivCar.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()
            val storageRef = Firebase.storage.reference
            val carImageRef = storageRef.child("cars/car666")
            val uploadTask = carImageRef.putBytes(data)
            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                carImageRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("MyLog", "${task.result.toString()}")
                }
            }
    }

    private fun entryParams(carID: Int) {
        viewModel.getCar(carID)
        binding.viewModel = viewModel
        viewModel.carItem.observe(viewLifecycleOwner) {
            it.imageUri?.let { it1 -> placePhoto(it1) }
        }
    }

}