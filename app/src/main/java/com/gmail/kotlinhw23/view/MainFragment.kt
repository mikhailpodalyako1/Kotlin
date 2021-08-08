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
import kotlin.properties.Delegates
import kotlin.properties.Delegates.notNull

class MainFragment : Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }
    private val viewModel: MainViewModel by lazy{
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private var _binding: MainFragmentBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = MainFragmentAdapter()
    private var isDataSetRus: Boolean = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter.setOnItemViewClickListener { weather ->
                activity?.supportFragmentManager?. apply{
                        beginTransaction()
                        .add(R.id.container, DetailsFragment.newInstance(Bundle().apply {
                            putParcelable(DetailsFragment.BUNDLE_EXTRA, weather)
                         }))
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
            }

        binding.mainFragmentRecyclerView.adapter = adapter
        binding.mainFragmentFAB.setOnClickListener {
            changeWeatherDataSet()
        }
        val observer = Observer<AppState> { a ->
            renderData(a)
        }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.getWeatherFromLocaleSourceWorld()
    }
    private fun changeWeatherDataSet() {
        if (isDataSetRus) {
            viewModel.getWeatherFromLocaleSourceRus()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_world)
        } else {
            viewModel.getWeatherFromLocaleSourceWorld()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
        }.also {
            isDataSetRus = !isDataSetRus
        }
    }
    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Success -> {
                binding.loadingLayout.hide()
                adapter.setWeather(data.weatherData)
            }
            is AppState.Loading -> {
                binding.loadingLayout.show()
            }
            is AppState.Error -> {
                binding.loadingLayout.hide()
                binding.mainFragmentFAB.showSnackBar("Error", "Reload please"){
                        if (isDataSetRus) viewModel.getWeatherFromLocaleSourceRus()
                        else viewModel.getWeatherFromLocaleSourceWorld()
                    }
            }
        }
    }
}
