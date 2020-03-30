package com.cityweather.ui.currentLocation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cityweather.R
import com.cityweather.network.model.ListItem



class ForeCastAdapter ():RecyclerView.Adapter<ForeCastViewHolder>(){
    private val forecastItems = mutableListOf<ListItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForeCastViewHolder {
        return ForeCastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.next_day_forecast,parent,false))
    }

    override fun getItemCount(): Int {
        return forecastItems.size
    }

    override fun onBindViewHolder(holder: ForeCastViewHolder, position: Int) {

        holder.binData(forecastItems[position])
    }

    fun addItems(listItem: List<ListItem>){
        forecastItems.addAll(listItem)
        notifyDataSetChanged()
    }

}


class ForeCastViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    val temperature = itemView.findViewById<TextView>(R.id.textViewTemp)
    val time = itemView.findViewById<TextView>(R.id.textViewTimeOfDay)
    val date = itemView.findViewById<TextView>(R.id.textViewDayOfWeek)
    val max = itemView.findViewById<TextView>(R.id.maxTemp)
    val min = itemView.findViewById<TextView>(R.id.minTemp)


    fun binData(item:ListItem){

        temperature.text = item.main?.getTempString()
        time.text = item.getHourOfDay()
        date.text = item.getDay()
        max.text = item.main?.getTempMaxString()
        min.text = item.main?.getTempMinString()
    }

}