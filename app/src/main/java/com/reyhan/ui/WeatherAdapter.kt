package com.reyhan.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reyhan.cuacaapp.BuildConfig
import com.reyhan.cuacaapp.databinding.RowItemWeatherBinding
import com.reyhan.data.ListItem
import com.reyhan.utils.HelperFunction.formatterDegree
import com.reyhan.utils.sizeIconWeather4x
import java.text.SimpleDateFormat
import java.util.*

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.MyViewHolder>() {
    private val listWeather = ArrayList<ListItem>()

    class MyViewHolder(val binding: RowItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        RowItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listWeather[position]
        holder.binding.apply {
            val maxTemp = "Max:" + formatterDegree(data.main?.tempMax)
            tvMaxDegree.text = maxTemp
            val minTemp = "Min:" + formatterDegree(data.main?.tempMin)
            tvMinDegree.text = minTemp

            val date = data.dtTxt?.take(10)
            val time = data.dtTxt?.takeLast(8)

            val dateArray = date?.split("-")?.toTypedArray()
            val timeArray = time?.split(":")?.toTypedArray()

            val calendar = Calendar.getInstance()
            // Date
            calendar.set(Calendar.YEAR, Integer.parseInt(dateArray?.get(0) as String))
            calendar.set(Calendar.MONTH, Integer.parseInt(dateArray[1]) - 1)
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateArray[2]))

            // Time
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray?.get(0) as String))
            calendar.set(Calendar.MINUTE, 0)

            val dateFormat = SimpleDateFormat("EEE, MMM, d", Locale.getDefault())
                .format(calendar.time).toString()
            tvItemDate.text = dateFormat
            val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
                .format(calendar.time).toString()
            tvItemTime.text = timeFormat

            val iconId = data.weather?.get(0)?.icon
            val iconUrl = BuildConfig.ICON_URL + iconId + sizeIconWeather4x
            Glide.with(imgItemWeather.context).load(iconUrl)
                .into(imgItemWeather)

        }
    }

    override fun getItemCount() = listWeather.size

    fun setData(data: List<ListItem>?) {
        if (data == null) return
        listWeather.clear()
        listWeather.addAll(data)
    }
}