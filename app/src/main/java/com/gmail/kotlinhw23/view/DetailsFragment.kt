package com.gmail.kotlinhw23.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.kotlinhw23.R
import com.gmail.kotlinhw23.databinding.DetaisFragmentBinding
import com.gmail.kotlinhw23.model.data.Weather
import kotlinx.android.synthetic.main.detais_fragment.*

class DetailsFragment : Fragment(){
    private var _binding: DetaisFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetaisFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather = arguments?.getParcelable<Weather>(BUNDLE_EXTRA)
        if (weather != null){
            populateData(weather)
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
    companion object {
        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle):DetailsFragment{
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}