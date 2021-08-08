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
        arguments?.getParcelable<Weather>(BUNDLE_EXTRA)?.let { weather ->  populateData(weather) }
    }

        private fun populateData(weatherData: Weather){
            with(binding){
                weatherData.city.also {city ->
                    cityName.text = city.city
                    cityCoordinates.text = String.format("lt/ln: %s, %s",
                       city.lat.toString(),
                        city.lon.toString()
                    ) }

                weatherData.apply {
                    temperatureValue.text =temperature.toString()
                    feelsLikeValue.text =feelsLike.toString() }


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