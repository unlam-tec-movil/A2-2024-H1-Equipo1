package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.domain.model.WeatherResponse
import ar.edu.unlam.mobile.scaffolding.domain.model.toCelsius
import ar.edu.unlam.mobile.scaffolding.domain.model.translateCondition
import ar.edu.unlam.mobile.scaffolding.ui.screens.HomeViewModel
import kotlin.math.roundToInt

@Composable
fun WeatherDisplay(
    weatherState: WeatherResponse?,
    viewModel: HomeViewModel,
) {
    weatherState?.let { weatherResponse ->
        val temperature = roundTemperature(temperature = weatherResponse.main.temp.toCelsius())
        val condition = translateCondition(weatherResponse.weather[0].main)

        val message =
            viewModel.getWeatherMessage(
                temperature,
                condition,
                weatherResponse.main.humidity,
                weatherResponse.wind.speed,
            )
        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        viewModel.getMessageBackgroundColor(message),
                        shape = RoundedCornerShape(8.dp),
                    )
                    .border(
                        16.dp,
                        viewModel.getMessageBackgroundColor(message),
                        shape = RoundedCornerShape(10.dp),
                    ),
        ) {
//
            Row(
                modifier =
                    Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                // personalizar
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                WeatherIcon(icon = viewModel.getWeatherEmoji(keyWeather = condition))
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier =
                        Modifier
                            .fillMaxHeight()
                            .background(Color.Transparent),
                ) {
                    CustomText(
                        text = "$temperature°C, $condition",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = message,
                        fontSize = 18.sp,
                    )
                }
            }
        }
    }
}

@Composable
fun roundTemperature(temperature: Double): Int {
    return temperature.roundToInt()
}
