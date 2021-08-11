package com.gmail.kotlinhw23.view

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.viewbinding.BuildConfig
import com.gmail.kotlinhw23.databinding.DetaisFragmentBinding
import com.gmail.kotlinhw23.model.data.Weather
import com.gmail.kotlinhw23.model.dto.WeatherDto
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors


class DetailsFragment : Fragment(){
    private var _binding: DetaisFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: Weather
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetaisFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable<Weather>(BUNDLE_EXTRA)?: Weather()
        binding.main.hide()
        binding.loadingLayout.show()
        val loader =WeatherLoader(onLoadListener, weatherBundle.city.lat, weatherBundle.city.lon)
        loader.loadWeather()

    }
    private val onLoadListener = object: WeatherLoader.WeatherLoaderListener{
        override fun onLoaded(weatherDto: WeatherDto) {
            displayWeather(weatherDto)
        }

        override fun onFinal(throwable: Throwable) {

        }

    }



        private fun displayWeather(weatherDto: WeatherDto){
            with(binding){
                main.show()
                loadingLayout.hide()
                weatherBundle.city.also {city ->
                    cityName.text = city.city
                    cityCoordinates.text = String.format("lt/ln: %s, %s",
                       city.lat.toString(),
                        city.lon.toString()
                    ) }

                weatherDto.fact?.let { fact ->
                        temperatureValue.text = fact.temp.toString()
                        feelsLikeValue.text = fact.feels_like.toString()
                        weatherConditions.text = fact.condition


                }
            }
        }
    companion object {
        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle):DetailsFragment{
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}