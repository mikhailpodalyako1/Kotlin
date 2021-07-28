package com.gmail.homework31

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.homework31.data.Weather
import com.gmail.homework31.repository.IRepository
import com.gmail.homework31.repository.Repository

class WeatherAdapter(private val repository: IRepository) :
        RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    class WeatherViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
        private val city: TextView
        private val temperature: TextView

        init {
            city = item.findViewById(R.id.textCity)
            temperature = item.findViewById(R.id.textTemp)
        }

        fun bind(weather: Weather) {
            city.text = weather.town
            temperature.text = weather.temperature.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(repository.getWeathers()[position])
    }

    override fun getItemCount(): Int {
        return repository.getWeathers().size
    }
    companion object Factory{
        private val ZeroTemp: Int = 0

        fun getInstanse(repository: IRepository):WeatherAdapter{
            return WeatherAdapter(repository)
        }
    }
}