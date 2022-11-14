package ru.delayvi.drive3.presentation.recycler_view

import androidx.recyclerview.widget.DiffUtil
import ru.delayvi.drive3.domain.entity.cars.Car

class CarDiffUtilCallback: DiffUtil.ItemCallback<Car>() {
    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem == newItem
    }
}