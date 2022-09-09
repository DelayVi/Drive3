package ru.delayvi.drive3.presentation.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.delayvi.drive3.R
import ru.delayvi.drive3.domain.entity.Car


class CarListAdapter() : ListAdapter<Car, CarViewHolder>(CarDiffUtilCallback()) {

    var onClickListener: ((Car) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false )
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CarViewHolder, position: Int) {
        val selectedCar = getItem(position)
        viewHolder.bind(selectedCar)
        viewHolder.itemView.setOnClickListener {
            onClickListener?.invoke(selectedCar)
        }
    }


}