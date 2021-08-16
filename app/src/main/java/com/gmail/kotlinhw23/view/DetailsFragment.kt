package com.gmail.kotlinhw23.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.viewbinding.BuildConfig
import com.gmail.kotlinhw23.R
import com.gmail.kotlinhw23.databinding.DetaisFragmentBinding
import com.gmail.kotlinhw23.model.data.Weather
import com.gmail.kotlinhw23.model.dto.FactDto
import com.gmail.kotlinhw23.model.dto.WeatherDto
import com.gmail.kotlinhw23.servise.DetailsServise
import com.gmail.kotlinhw23.servise.LATITUDE_EXTRA
import com.gmail.kotlinhw23.servise.LONGITUDE_EXTRA
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors

const val DETAILS_INTENT_FILTER = "DETAILS INTENT FILTER"
const val DETAILS_LOAD_RESULT_EXTRA = "LOAD RESULT"
const val DETAILS_INTENT_EMPTY_EXTRA = "INTENT IS EMPTY"
const val DETAILS_DATA_EMPTY_EXTRA = "DATA IS EMPTY"
const val DETAILS_REPOSITORY_EMPTY_EXTRA = "REPOSITORY IS EMPTY"
const val DETAILS_REQUEST_ERROR_EXTRA = "REQUEST ERROR"
const val DETAILS_REQUEST_ERROR_MESSAGE_EXTRA = "REQUEST ERROR MESSAGE"
const val DETAILS_URL_MALFORMED_EXTRA = "URL MALFORMED"
const val DETAILS_RESPONSE_SUCCESS_EXTRA = "RESPONSE SUCCESS"
const val DETAILS_TEMP_EXTRA = "TEMPERATURE"
const val DETAILS_FEELS_LIKE_EXTRA = "FEELS LIKE"
const val DETAILS_CONDITIONS_EXTRA = "CONDITIONS"
private const val TEMP_INVALID = -100
private const val FEELS_LIKE_INVALID = -100
private const val PROCESS_ERROR = "ERROR HANDLING"


class DetailsFragment : Fragment() {
    private var _binding: DetaisFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: Weather

    private val loadResultsReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.getStringExtra(DETAILS_LOAD_RESULT_EXTRA)) {
                DETAILS_INTENT_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_DATA_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_REPOSITORY_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_REQUEST_ERROR_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_REQUEST_ERROR_MESSAGE_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_URL_MALFORMED_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_RESPONSE_SUCCESS_EXTRA -> renderData(
                    WeatherDto(
                        FactDto(
                            intent.getIntExtra(
                                DETAILS_TEMP_EXTRA, TEMP_INVALID
                            ),
                            intent.getIntExtra(DETAILS_FEELS_LIKE_EXTRA, FEELS_LIKE_INVALID),
                            intent.getStringExtra(
                                DETAILS_CONDITIONS_EXTRA
                            )
                        )
                    )
                )
                else -> TODO(PROCESS_ERROR)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetaisFragmentBinding.inflate(inflater, container, false)
        context?.let {
            LocalBroadcastManager.getInstance(it)
                .registerReceiver(loadResultsReceiver, IntentFilter(DETAILS_INTENT_FILTER))
        }
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(loadResultsReceiver)
        }
        super.onDestroyView()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable<Weather>(BUNDLE_EXTRA) ?: Weather()
        getWeather()
    }

    private fun getWeather() {
        binding.main.hide()
        binding.loadingLayout.show()
        context?.let {
            it.startService(Intent(it, DetailsServise::class.java).apply {
                putExtra(
                    LATITUDE_EXTRA,
                    weatherBundle.city.lat
                )
                putExtra(
                    LONGITUDE_EXTRA,
                    weatherBundle.city.lon
                )
            })
        }
    }

    private fun renderData(weatherDTO: WeatherDto) {
        binding.main.show()
        binding.loadingLayout.hide()

        val fact = weatherDTO.fact
        val temp = fact!!.temp
        val feelsLike = fact.feels_like
        val condition = fact.condition
        if (temp == TEMP_INVALID || feelsLike == FEELS_LIKE_INVALID || condition == null) {
            TODO(PROCESS_ERROR)
        } else {
            val city = weatherBundle.city
            binding.cityName.text = city.city
            binding.cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                city.lat.toString(),
                city.lon.toString()
            )
            binding.temperatureValue.text = temp.toString()
            binding.feelsLikeValue.text = feelsLike.toString()
            binding.weatherConditions.text = condition
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