package ru.delayvi.drive3.presentation

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.entity.Color
import ru.delayvi.drive3.domain.entity.Fuel

@BindingAdapter("setPriceToViewHolder")
fun setPriceToViewHolder(textView: TextView, text: String) {
    val price = text.reversed().chunked(3).joinToString(" ").reversed()
    textView.text = "$price ₽"
}

@BindingAdapter("setEngineToViewHolder")
fun setEngineToViewHolder(textView: TextView, volume: String?) {
    textView.text = (volume?.toDouble()?.times(100).toString().take(3) + "л.с.") ?: ""
}

@BindingAdapter("setEngineToCarFragment")
fun setEngineToCarFragment(textView: TextView, volume: String?) {
    textView.text = volume?.take(3) ?: ""
}

@BindingAdapter("setEquipmentToViewHolder")
fun setEquipmentToViewHolder(textView: TextView, car: Car) {
    if (car.engine.toDouble() < 2.5)
    textView.text = car.engine.take(3) + "L AT AWD Luxury"
    else
        textView.text = car.engine.take(3) + "L AT AWD Sport"
}

@BindingAdapter("getNameFromBrandAndModel")
fun getNameFromBrandAndModel(textView: TextView, car: Car) {
    textView.text = "${car.brand.substring(0, 1).uppercase()}${car.brand.substring(1)} " +
            "${car.model.substring(0, 1).uppercase()}${car.model.substring(1)} ${car.year}"
}

@BindingAdapter("setFuelToViewHolder")
fun setFuelToViewHolder(textView: TextView, fuel: Fuel) {
    textView.text = when (fuel) {
        Fuel.DIESEL -> "Дизель"
        Fuel.ELECTRIC -> "Электро"
        Fuel.HYBRID -> "Гибрид"
        Fuel.GASOLINE -> "Бензин"
    }
}


@BindingAdapter("setColorToViewHolder")
fun setColorToViewHolder(textView: TextView, color: Color) {
    textView.text = when (color) {
        Color.BLACK -> "Чёрный"
        Color.BLUE -> "Синий"
        Color.RED -> "Красный"
        Color.WHITE -> "Белый"
        Color.GREEN -> "Зелёный"
        Color.YELLOW -> "Жёлтый"
    }
}