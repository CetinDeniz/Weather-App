package com.example.learning.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.learning.database.entity.DailyForecast
import com.example.learning.database.entity.HourlyForecast
import com.example.learning.database.entity.Weather
import com.example.learning.databinding.DailyVerticalListItemBinding
import com.example.learning.databinding.HourlyListItemBinding

private const val ITEM_VIEW_TYPE_HOURLY = 0
private const val ITEM_VIEW_TYPE_DAILY = 1

class CommonAdapter(private val clickListener: DailyWeatherListener) :
    ListAdapter<Weather, RecyclerView.ViewHolder>(CommonDiffUtilCallback) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is HourlyForecast -> ITEM_VIEW_TYPE_HOURLY
            is DailyForecast -> ITEM_VIEW_TYPE_DAILY
            else -> throw ClassNotFoundException()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HOURLY -> HourlyViewHolder.from(parent)
            ITEM_VIEW_TYPE_DAILY -> DailyViewHolder.from(parent)
            else -> throw ClassCastException("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        /* Hourly or Daily data for bind */
        when (holder) {
            is HourlyViewHolder -> {
                holder.bind(getItem(position) as HourlyForecast)
            }
            is DailyViewHolder -> {
                holder.bind(getItem(position) as DailyForecast, clickListener)
            }
        }
    }


    class HourlyViewHolder private constructor(private val binding: HourlyListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hourlyForecast: HourlyForecast) {
            binding.hourlyForecast = hourlyForecast
        }

        companion object {
            fun from(parent: ViewGroup): HourlyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HourlyListItemBinding.inflate(layoutInflater, parent, false)
                return HourlyViewHolder(binding)
            }
        }
    }

    class DailyViewHolder private constructor(private val binding: DailyVerticalListItemBinding) :  /* DailyListItemBinding */
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dailyForecast: DailyForecast, clickListener: DailyWeatherListener) {
            binding.dailyForecast = dailyForecast
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): DailyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DailyVerticalListItemBinding.inflate(layoutInflater,
                    parent,
                    false) /* DailyListItemBinding */
                return DailyViewHolder(binding)
            }
        }
    }

    object CommonDiffUtilCallback : DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem == newItem
        }
    }
}

class DailyWeatherListener(private val clickListener: (DailyForecast) -> Unit) {
    fun onClick(day: DailyForecast) = clickListener(day)
}