package ru.delayvi.drive3.presentation.recycler_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.delayvi.drive3.R
import ru.delayvi.drive3.databinding.CarItemBinding
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.entity.Color

class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = CarItemBinding.bind(itemView)
    fun bind(car: Car) = with(binding) {
        with(car) {
            tvBrand.text = brand
            tvModel.text = model
            tvPrice.text = price + " RUB"
            tvEngine.text = engine.take(3) + " L"
            tvColor.text = when (color) {
                Color.BLACK -> "Чёрный"
                Color.BLUE -> "Синий"
                Color.RED -> "Красный"
                Color.WHITE -> "Белый"
                Color.GREEN -> "Зелёный"
            }
            Picasso.get()
                .load(imageUri)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.carImage)
        }

    }

}