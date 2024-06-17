package ar.edu.unlam.mobile.scaffolding.domain.model

import com.google.gson.annotations.SerializedName

const val KELVIN: Double = 273.0

data class WeatherResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val rain: Rain?,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int,
)

data class Coord(
    val lon: Double,
    val lat: Double,
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
)

data class Main(
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("sea_level")
    val seaLevel: Int?,
    @SerializedName("grnd_level")
    val grndLevel: Int?,
)

fun Double.toCelsius(): Double = (this - KELVIN)

fun translateCondition(condition: String): String =
    when (condition) {
        "Clear" -> "Despejado"
        "Clouds" -> "Nublado"
        "Rain" -> "Lluvia"
        "Snow" -> "Nieve"
        "Thunderstorm" -> "Tormenta"
        else -> condition
    }

data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double?,
)

data class Rain(
    @SerializedName("1h")
    val oneHour: Double,
)

data class Clouds(
    val all: Int,
)

data class Sys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long,
)
