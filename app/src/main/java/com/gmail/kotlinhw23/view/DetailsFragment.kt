 package com.gmail.kotlinhw23.view

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gmail.kotlinhw23.BuildConfig
import com.gmail.kotlinhw23.R
import com.gmail.kotlinhw23.databinding.DetaisFragmentBinding
import com.gmail.kotlinhw23.model.AppState
import com.gmail.kotlinhw23.model.data.Weather
import com.gmail.kotlinhw23.model.dto.WeatherDto
import com.gmail.kotlinhw23.viewmodel.DetailsViewModel

import com.google.gson.Gson
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.IOException
import kotlin.jvm.Throws


 private const val TEMP_INVALID = -100
 private const val FEELS_LIKE_INVALID = -100
 private const val PROCESS_ERROR = "Обработка ошибки"
 private const val REQUEST_API_KEY = "X-Yandex-API-Key"
 class DetailsFragment : Fragment() {
     private var _binding: DetaisFragmentBinding? = null
     private val binding get() = _binding!!
     private lateinit var weatherBundle: Weather
     private val viewModel: DetailsViewModel by lazy {
         ViewModelProvider(this).get(DetailsViewModel::class.java)
     }
     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View {
         _binding = DetaisFragmentBinding.inflate(inflater, container, false)
         return binding.root
     }
     override fun onDestroyView() {
         _binding = null
         super.onDestroyView()
     }
     @RequiresApi(Build.VERSION_CODES.N)
     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         weatherBundle = arguments?.getParcelable<Weather>(BUNDLE_EXTRA) ?: Weather()
         viewModel.detailsLiveData.observe(viewLifecycleOwner) { renderData(it) }
         viewModel.getWeatherFromRemoteSource(weatherBundle.city.lat, weatherBundle.city.lon)
     }
     private fun renderData(appState: AppState) {
         when (appState) {
             is AppState.Success -> {
                 binding.main.show()
                 binding.loadingLayout.hide()
                 setWeather(appState.weatherData[0])
             }
             is AppState.Loading -> {
                 binding.main.hide()
                 binding.loadingLayout.show()
             }
             is AppState.Error -> {
                 binding.main.show()
                 binding.loadingLayout.hide()
                 binding.main.showSnackBar(getString(R.string.error), getString(R.string.reload)) {
                     viewModel.getWeatherFromRemoteSource(weatherBundle.city.lat, weatherBundle.city.lon)
                 }
             }
         }
     }
     private fun setWeather(weather: Weather) {
         with(binding) {
             weatherBundle.city.let { city ->
                 cityName.text = city.city
                 cityCoordinates.text = String.format(
                     getString(R.string.city_coordinates),
                     city.lat.toString(),
                     city.lon.toString()
                 )
             }
             weather.let {
                 temperatureValue.text = it.temperature.toString()
                 feelsLikeValue.text = it.feelsLike.toString()
                 weatherCondition.text = it.condition
             }
             Picasso
                 .get()
                 .load("https://freepngimg.com/thumb/city/163970-city-skyline-york-free-transparent-image-hq.png")
                 .into(headerIcon)
         }
     }

     companion object {
         const val BUNDLE_EXTRA = "weather"
         fun newInstance(bundle: Bundle): DetailsFragment {
             val fragment = DetailsFragment()
             fragment.arguments = bundle
             return fragment
         }
     }
 }