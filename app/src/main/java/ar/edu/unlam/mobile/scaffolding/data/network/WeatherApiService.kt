package ar.edu.unlam.mobile.scaffolding.data.network

import ar.edu.unlam.mobile.scaffolding.domain.model.WeatherResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") locationKey: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String,
    ): WeatherResponse

    companion object {
        fun create(): WeatherApiService {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client =
                OkHttpClient
                    .Builder()
                    .addInterceptor(logging)
                    .build()

            val retrofit =
                Retrofit
                    .Builder()
                    .baseUrl("https://api.openweathermap.org/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(WeatherApiService::class.java)
        }
    }
}
