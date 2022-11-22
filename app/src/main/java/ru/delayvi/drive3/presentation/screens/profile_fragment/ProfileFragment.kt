package ru.delayvi.drive3.presentation.screens.profile_fragment


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.delayvi.drive3.databinding.FragmentProfileBinding
import ru.delayvi.drive3.di.DaggerAppComponent
import ru.delayvi.drive3.domain.entity.users.LoggedForm
import ru.delayvi.drive3.presentation.recycler_view.CarListAdapter
import javax.inject.Inject

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    @Inject
    lateinit var viewModelFactory: ProfileViewModelFactory

    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
    }

    private val appComponent by lazy {
        DaggerAppComponent.factory().create(requireActivity().application)
    }

    private var carListAdapter = CarListAdapter()

    override fun onAttach(context: Context) {
        appComponent.inject(this)
        super.onAttach(context)
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonSignIn.setOnClickListener {
                if ((etLogin.text.toString().isBlank()) || (etPassword.text.toString().isBlank()))
                    Toast.makeText(requireContext(), "Не все поля заполнены", Toast.LENGTH_LONG).show()
                else viewModel.signIn(etLogin.text.toString(), etPassword.text.toString())
            }
            buttonSignUp.setOnClickListener {
                viewModel.signUpTest(LoggedForm(etLogin.text.toString(), etPassword.text.toString()))
            }
            buttonLogout.setOnClickListener {
                viewModel.logout()
            }

            viewModel.isSignedIn.observe(viewLifecycleOwner) {
                Log.d("MyLog", "srabotal observe")
                tvIsAuthResult.text = it.toString()
                tvCurrentUserDisplayName.text = viewModel.getCurrentUserView()
                Log.d("MyLog", "isAuthorized in fragment: $it , current userVIew ${viewModel.getCurrentUserView()}")
            }


        }
    }
}