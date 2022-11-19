package ru.delayvi.drive3.presentation.screens.search_fragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.parse.ParseObject
import com.parse.ParseUser
import com.parse.SignUpCallback
import ru.delayvi.drive3.R
import ru.delayvi.drive3.databinding.FragmentMainBinding
import ru.delayvi.drive3.databinding.FragmentSearchBinding
import ru.delayvi.drive3.di.DaggerAppComponent
import ru.delayvi.drive3.domain.entity.users.LoggedForm
import ru.delayvi.drive3.presentation.recycler_view.CarListAdapter
import ru.delayvi.drive3.presentation.screens.main_fragment.MainFragmentDirections
import ru.delayvi.drive3.presentation.screens.main_fragment.MainViewModel
import ru.delayvi.drive3.presentation.screens.main_fragment.MainViewModelFactory
import javax.inject.Inject

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    @Inject
    lateinit var viewModelFactory: SearchViewModelFactory

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
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
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

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

//    private fun showSignInDialog() {
//        val dialog = BottomSheetDialog(requireContext())
//        dialog.setContentView(R.layout.sign_in_bottom_sheet)
//        val buttonSignIn = dialog.findViewById<Button>(R.id.buttonSignIn)
//        val buttonSignUp = dialog.findViewById<Button>(R.id.buttonSignUp)
//        val etLogin = dialog.findViewById<EditText>(R.id.etLogin)
//        val etPassword = dialog.findViewById<EditText>(R.id.etPassword)
//        val tvName = dialog.findViewById<TextView>(R.id.tvCurrentUserDisplayName)
//
//        buttonSignIn?.setOnClickListener {
//            viewModel.signIn(etLogin?.text.toString(), etPassword?.text.toString())
//        }
//        buttonSignUp?.setOnClickListener {
//            viewModel.signUpTest(LoggedForm(etLogin?.text.toString(), etPassword?.text.toString()))
//        }
//
//        viewModel.currentUserView.observe(viewLifecycleOwner) {
//            tvName?.text = it.displayName
//            Log.d("MyLog", "search fragment current user: $it")
//        }
//        viewModel.isAuthorized.observe(viewLifecycleOwner) {
//            Toast.makeText(requireContext(), "Authorized: $it", Toast.LENGTH_SHORT).show()
//            Log.d("MyLog", "isAuthorized in fragment: $it")
//        }
//
//        dialog.show()
//
//
//    }


    private fun launchShowCarFragment(carID: Int) {
        val entity = ParseObject("Radjab");

        entity.put("name", "Vadim Alyamkin");

        entity.saveInBackground {
            if (it != null) {
                it.localizedMessage?.let { message -> Log.e("MainActivity", message) }
            } else {
                Log.d("MainActivity", "Object saved.")
                Toast.makeText(requireContext(), "Object saved.", Toast.LENGTH_LONG).show()
            }
        }
        // findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToShowCarFragment(carID))
    }

    private fun setupRecyclerView() {
        carListAdapter = CarListAdapter()
        binding.rvCar.adapter = carListAdapter
    }


}