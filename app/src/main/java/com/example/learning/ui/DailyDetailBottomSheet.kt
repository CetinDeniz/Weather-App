package com.example.learning.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.learning.databinding.FragmentDailyDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DailyDetailBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentDailyDetailBinding.inflate(inflater)

        binding.dailyWeather = arguments?.getParcelable("dailyWeather")

        return binding.root
    }

}