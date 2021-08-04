package com.gmail.kotlinhw23.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.gmail.kotlinhw23.R
import com.gmail.kotlinhw23.databinding.MainFragmentBinding
import com.gmail.kotlinhw23.model.AppState
import com.gmail.kotlinhw23.model.data.Weather
import com.gmail.kotlinhw23.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val observer = Observer<AppState> {a -> renderData(a)
        }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.getWeatherFromRemoteSourse()
    }

    private fun renderData(data: AppState) {

        when (data) {
            is AppState.Success -> {
                val weatherData = data.weatherData
                binding.loadingLayout.visibility = View.GONE
                populateData(weatherData)
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.main, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getWeatherFromRemoteSourse() }
                    .show()
            }
        }
    }

        private fun populateData(weatherData: Weather){
            with(binding){
                cityName.text = weatherData.city.city
                cityCoordinates.text = String.format(getString(R.string.city_coordinates),
                weatherData.city.lat.toString(),
                weatherData.city.lon.toString()
                )
                temperatureValue.text = weatherData.temperature.toString()
                feelsLikeValue.text = weatherData.feelsLike.toString()

            }
        }

    }
