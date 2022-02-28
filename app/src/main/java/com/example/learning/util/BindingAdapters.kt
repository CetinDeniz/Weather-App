package com.example.learning.util

import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.learning.R
import com.example.learning.database.entity.DailyForecast
import com.example.learning.database.entity.HourlyForecast
import com.example.learning.database.entity.Weather
import com.example.learning.network.ICON_URL_FIRST
import com.example.learning.network.ICON_URL_SECOND
import com.example.learning.ui.adapter.CommonAdapter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Status when call made, gonna use later
 */
//@BindingAdapter("status")
//fun status(imageView: ImageView, status: Status?) {
//    with(imageView) {
//        when (status) {
//            Status.LOADING -> this.setImageResource(R.drawable.loading_animation)
//                .also { visibility = View.VISIBLE }
//            Status.DONE -> this.visibility = View.GONE
//            Status.ERROR -> this.setImageResource(R.drawable.ic_connection_error)
//                .also { visibility = View.VISIBLE }
//        }
//    }
//}

/**
 * Image Downloader
 */
@BindingAdapter("imageUrl")
fun imageUrl(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.uriBuilder().toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

/**
 * To capitalize the first characters of the words of the weather description
 */
@BindingAdapter("weatherDescription")
fun TextView.setWeatherDescription(description: String?) {
    description?.let {
        text = description.split(" ").joinToString(" ") { word ->
//            word.first().uppercase() + word.substring(1) /* Other way of doing */
            word.replaceFirstChar {
                it.uppercase()
            }
        }
    }
}

/**
 * Image Uri Builder
 */
private fun String.uriBuilder(): String {
    return ICON_URL_FIRST + this + ICON_URL_SECOND
}

/**
 * DailyListAdapter data submitter
 * Web service returns total 8 day with current day also, in this method current day is ignored
 */
@BindingAdapter("recyclerViewSubmit")
fun RecyclerView.setHourlyAndDailyWeather(data: List<Weather>?) {
    data?.let {
        when (data[0]) {
            is HourlyForecast -> {
                (adapter as CommonAdapter) /* HourlyListAdapter */
                    .submitList(it.subList(1, 25) as List<HourlyForecast>)
            }
            is DailyForecast -> {
                (adapter as CommonAdapter) /* DailyListAdapter */
                    .submitList(it.subList(1, it.size) as List<DailyForecast>)
            }
        }
    }
}

/**
 * Time Formatters
 */
@BindingAdapter("timeFormat")
fun TextView.setTime(time: Int) {
//    val dateFormatter = SimpleDateFormat("h:mm a", Locale.US)
//        dateFormatter.timeZone = TimeZone.getTimeZone("UTC")
    text = SimpleDateFormat("h:mm a", Locale.US).format(
        Date(time * 1000L)
    )
}

@BindingAdapter("dayInTheWeek")
fun TextView.dayInTheWeek(time: Int) {
    text = SimpleDateFormat("EEEE", Locale.US).format(
        Date(time.toLong() * 1000L)
    )
}

/**
 * Is favorite ?
 */
@BindingAdapter("checked")
fun ToggleButton.isChecked(flag: Int){
    isChecked = flag == 1
}
