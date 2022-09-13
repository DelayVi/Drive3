package ru.delayvi.drive3.presentation

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ru.delayvi.drive3.domain.entity.Color

@BindingAdapter("setPriceToViewHolder")
fun setPriceToViewHolder(textView: TextView, text: String){
    textView.text = "$text RUB"
}

@BindingAdapter("setEngineToViewHolder")
fun setEngineToViewHolder(textView: TextView, volume: String){
    textView.text = volume.take(3) + "L"
}

@BindingAdapter("setColorToViewHolder")
fun setColorToViewHolder(textView: TextView, color: Color){
    textView.text = when(color){
        Color.BLACK -> "Чёрный"
        Color.BLUE -> "Синий"
        Color.RED -> "Красный"
        Color.WHITE -> "Белый"
        Color.GREEN -> "Зелёный"
    }
}