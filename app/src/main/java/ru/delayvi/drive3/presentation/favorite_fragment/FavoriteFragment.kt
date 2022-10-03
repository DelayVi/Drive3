package ru.delayvi.drive3.presentation.favorite_fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.delayvi.drive3.databinding.FragmentFavoriteBinding
import ru.delayvi.drive3.di.DaggerAppComponent
import ru.delayvi.drive3.presentation.recycler_view.CarListAdapter
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    @Inject
    lateinit var viewModelFactory: FavoriteViewModelFactory

    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavoriteViewModel::class.java]
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
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.carList.observe(viewLifecycleOwner) {
            carListAdapter.submitList(it)
        }

        carListAdapter.onClickListener = {
            Log.d("MyLog", it.isFavorite.toString())
        }
    }

    private fun setupRecyclerView() {
        carListAdapter = CarListAdapter()
        binding.rvFavoriteCars.adapter = carListAdapter
        swipeToRemove(binding.rvFavoriteCars)
    }

    private fun swipeToRemove(recyclerView: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = carListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.makeFavorite(item.id)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
    companion object {
        fun newInstance(): FavoriteFragment {
            return FavoriteFragment()
        }

    }
}