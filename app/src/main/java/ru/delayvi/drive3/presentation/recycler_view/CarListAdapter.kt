package ru.delayvi.drive3.presentation.recycler_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import ru.delayvi.drive3.R
import ru.delayvi.drive3.databinding.CarItemBinding
import ru.delayvi.drive3.databinding.SearchItemBinding
import ru.delayvi.drive3.domain.entity.Car


class CarListAdapter() : ListAdapter<Car, CarViewHolder>(CarDiffUtilCallback()) {

    var onClickListener: ((Car) -> Unit)? = null
    var onFavoriteStarClickListener: ((Car) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding =  CarItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CarViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(viewHolder: CarViewHolder, position: Int) {
        val binding = viewHolder.binding
        if (binding is CarItemBinding) {
            val selectedCar = getItem(position)
            binding.carProperties = selectedCar
            Picasso.get()
                .load(selectedCar.imageUri)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.carImage)
            if (!selectedCar.isFavorite) {
                binding.ivFavorite.setImageResource(R.drawable.ic_baseline_favorites_24)
            } else {
                binding.ivFavorite.setImageResource(R.drawable.ic_baseline_is_favorite_24)
            }
            viewHolder.itemView.setOnClickListener {
                onClickListener?.invoke(selectedCar)
            }
            binding.ivFavorite.setOnClickListener {
                onFavoriteStarClickListener?.invoke(selectedCar)
            }
        }
    }
}