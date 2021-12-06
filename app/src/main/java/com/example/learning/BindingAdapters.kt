package com.example.learning

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import com.example.learning.data.Status
import com.example.learning.data.WeatherViewModel
import com.example.learning.network.ICON_URL

@BindingAdapter("status")
fun status(imageView: ImageView, status: Status?) {
    with(imageView) {
        when (status) {
            Status.LOADING -> this.setImageResource(R.drawable.loading_animation)
                .also { visibility = View.VISIBLE }
            Status.DONE -> this.visibility = View.GONE
            Status.ERROR -> this.setImageResource(R.drawable.ic_connection_error)
                .also { visibility = View.VISIBLE }
        }
    }
}

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
 * String ->                       String ->                                  Uri
 * 10d    ->  "http://openweathermap.org/img/wn/{iconCode}@2x.png"      ->   toUri
 *
 */
private fun String.uriBuilder(): String {
    val firstPart = "http://openweathermap.org/img/wn/"
    val secondPart = "@2x.png"

    return firstPart + this + secondPart
}