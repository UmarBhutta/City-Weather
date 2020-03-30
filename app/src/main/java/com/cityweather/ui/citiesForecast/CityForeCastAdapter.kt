package com.cityweather.ui.citiesForecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cityweather.R
import com.cityweather.network.model.CurrentWeatherResponse
import com.cityweather.network.model.ForecastResponse

class CityForeCastAdapter():RecyclerView.Adapter<CityForeCastViewHolder>() {
    private val data = mutableListOf<CurrentWeatherResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityForeCastViewHolder {
        return CityForeCastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_city,parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CityForeCastViewHolder, position: Int) {
        val item = data[position]
        holder.bindData(item)


    }

    fun addItemToList(item:CurrentWeatherResponse){
        data.add(item)
        notifyDataSetChanged()
    }
}

class CityForeCastViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    val cityName = itemView.findViewById<TextView>(R.id.city_name)
    val temp = itemView.findViewById<TextView>(R.id.temp)
    val description = itemView.findViewById<TextView>( R.id.description)
    val windSpeed = itemView.findViewById<TextView>(R.id.wind_speed)

    fun bindData(item:CurrentWeatherResponse){
        cityName.text = item.name
        temp.text = "Temperature Max: "+item.main?.getTempMaxString() + " Min: "+item.main?.getTempMinString()
        description.text = item.weather?.first()?.getDescriptionText()
        windSpeed.text = item.wind?.speed.toString()


    }
}

