package ru.delayvi.drive3.presentation.binding_adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ru.delayvi.drive3.domain.entity.cars.Car
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("setCountryAndDate")
fun setCountryAndDate(textView: TextView, car: Car?) {
    val day = SimpleDateFormat("dd").format(Date())
    val month = when (SimpleDateFormat("M").format(Date())) {
        "1" -> "января"
        "2" -> "февраля"
        "3" -> "марта"
        "4" -> "апреля"
        "5" -> "мая"
        "6" -> "июня"
        "7" -> "июля"
        "8" -> "августа"
        "9" -> "сентября"
        "10" -> "октября"
        "11" -> "ноября"
        "12" -> "декабря"
        else -> throw RuntimeException("Unknown month")
    }
    textView.text = "Барнаул, Алтайский край, $day $month "
}

@BindingAdapter("setModelBrandAndYearToTitle")
fun setModelBrandAndYearToTitle(textView: TextView, car: Car?) {
    textView.text = "${car?.brand} ${car?.model}, ${car?.year}"
}

@BindingAdapter("setPrice")
fun setPrice(textView: TextView, price: String?) {
    textView.text = "${price?.reversed()?.chunked(3)?.joinToString(" ")?.reversed()} ₽"
}

@BindingAdapter("setToTakeCredit")
fun setToTakeCredit(textView: TextView, price: String?) {
    val monthlyPayment = price?.toInt()?.div(50).toString()
    textView.text =
        "В кредит от ${monthlyPayment?.reversed()?.chunked(3)?.joinToString(" ")?.reversed()} ₽/мес."
}