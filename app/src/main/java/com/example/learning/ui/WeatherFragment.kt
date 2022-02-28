package com.example.learning.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.learning.R
import com.example.learning.databinding.FragmentWeatherBinding
import com.example.learning.ui.adapter.CommonAdapter
import com.example.learning.ui.adapter.DailyWeatherListener
import com.google.android.material.snackbar.Snackbar

class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private val weatherViewModel: WeatherViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this,
            WeatherViewModelFactory(activity.application)).get(WeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            lifecycleOwner = this@WeatherFragment
            viewModel = weatherViewModel

            val weatherClickListener = DailyWeatherListener {
                findNavController().navigate(
                    WeatherFragmentDirections.actionWeatherFragmentToDailyDetailBottomSheet(it)
                )
            }
            dailyRecyclerView.adapter = CommonAdapter(weatherClickListener)
            hourlyRecyclerView.adapter = CommonAdapter(weatherClickListener)

            favorite.setOnClickListener { favorite ->
                if ((favorite as ToggleButton).isChecked) {
                    Snackbar.make(favorite, "Added to DB", Snackbar.LENGTH_SHORT).show()
                    weatherViewModel.addFavorites()
                } else {
                    Snackbar.make(favorite, "Deleted from DB", Snackbar.LENGTH_SHORT)
                        .setAction("UNDO") {
                            weatherViewModel.addFavorites()
                            favorite.isChecked = true
                            Snackbar.make(favorite, "Restored", Snackbar.LENGTH_SHORT).show()
                        }.show()

                    weatherViewModel.removeFromFavorites()
                }
            }

            buttonAdd.setOnClickListener {
                findNavController()
                    .navigate(R.id.action_weatherFragment_to_addFragment)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.weather_fragment_menu, menu)

        val menuItem = menu.findItem(R.id.search)
        val searchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(input: String?): Boolean {
                input?.let {
                    weatherViewModel.makeCall(it)

                    Snackbar.make(
                        binding.root,
                        "Call Made",
                        Snackbar.LENGTH_SHORT
                    ).show()

                    hideKeyboard()
                    return true
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean = true
        })
    }

    private fun hideKeyboard() {
        requireActivity().currentFocus?.let {
            val inputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken,
                InputMethodManager.RESULT_UNCHANGED_SHOWN)
        }
    }

}