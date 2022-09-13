package ru.delayvi.drive3.presentation.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import ru.delayvi.drive3.R
import ru.delayvi.drive3.databinding.CarItemBinding
import ru.delayvi.drive3.domain.entity.Car


class CarListAdapter() : ListAdapter<Car, CarViewHolder>(CarDiffUtilCallback()) {

    var onClickListener: ((Car) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = CarItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: CarViewHolder, position: Int) {
        val binding = viewHolder.binding
        val selectedCar = getItem(position)
        binding.carProperties = selectedCar
        Picasso.get()
            .load(selectedCar.imageUri)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.carImage)
        viewHolder.itemView.setOnClickListener {
            onClickListener?.invoke(selectedCar)
        }
    }
}