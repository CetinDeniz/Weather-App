package com.example.learning

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.learning.data.WeatherViewModel
import com.example.learning.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_weather, container, false)


        binding.buttonAdd.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_weatherFragment_to_addFragment)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            lifecycleOwner = this@WeatherFragment
            viewModel = weatherViewModel
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.weather_fragment_menu, menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(input: String?): Boolean {
                input?.let {
                    weatherViewModel.makeCall(it)
                    Toast.makeText(context, "Call Made", Toast.LENGTH_SHORT).show()
                    return true
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean = true
        })
    }

}