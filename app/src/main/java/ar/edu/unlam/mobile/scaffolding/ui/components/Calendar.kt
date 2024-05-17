package ar.edu.unlam.mobile.scaffolding.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.R
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarView() {
    val currentDate = LocalDate.now()
    val year = currentDate.year
    val month = currentDate.monthValue
    val daysInMonth = LocalDate.of(year, month, 1).lengthOfMonth()
    val firstDayOfMonth = LocalDate.of(year, month, 1)
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek
    val nameDaysOfWeek = listOf("Lu", "Ma", "Mi", "Ju", "Vi", "Sa", "Do")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
            Modifier
                .fillMaxWidth(),
    ) {
        Text(
            text = currentDate.month.name,
            fontSize = 32.sp,
            fontFamily = FontFamily(Font(R.font.anni_use_your_telescope)),
            textAlign = TextAlign.Center,
        )

        Card {
            // Agregar fila con los nombres de los días de la semana
            Row {
                for (name in nameDaysOfWeek) {
                    Text(
                        text = name,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.anni_use_your_telescope)),
                        fontSize = 24.sp,
                        modifier =
                            Modifier
                                .weight(1f),
                    )
                }
            }

            LazyVerticalGrid(GridCells.Fixed(7)) {
                // Agregar días al mes
                val firstDayOfWeekValue = firstDayOfWeek.value
                val lastDayOfMonth = firstDayOfMonth.plusDays(daysInMonth.toLong() - 1)
                val lastDayOfWeekValue = lastDayOfMonth.dayOfWeek.value
                val totalDaysToShow =
                    daysInMonth + (firstDayOfWeekValue - 1) + (7 - lastDayOfWeekValue)

                items(totalDaysToShow) { index ->

                    VerticalDivider(
                        modifier = Modifier.fillMaxHeight(), // Ancho máximo de la línea
                        thickness = 1.dp, // Grosor de la línea
                        color = Color.Gray, // Color de la línea
                    ) // No se porque se pintan las lineas horizontales y no las verticales

                    val currentDay: Int? =
                        when {
                            index < firstDayOfWeekValue - 1 -> null // Antes del primer día del mes
                            index >= firstDayOfWeekValue - 1 + daysInMonth -> null // Después del último día del mes
                            else -> index - firstDayOfWeekValue + 2 // Día válido del mes
                        }
                    if (currentDay != null) {
                        DayCell(currentDay.toString())
                    } else {
                        EmptyDayCell()
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyDayCell() {
    Box(
        modifier =
            Modifier
                .size(50.dp)
                .background(color = Color.White),
    ) // Espacio en blanco para representar un día vacío
    HorizontalDivider(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(3.dp),
        // Ancho máximo de la línea
        thickness = 1.dp, // Grosor de la línea
        color = Color.Gray, // Color de la línea
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayCell(day: String) {
    Box(
        modifier =
            Modifier
                .size(50.dp)
                .background(color = Color.White),
    ) {
        Text(
            text = day,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.anni_use_your_telescope)),
            textAlign = TextAlign.Start,
            modifier =
                Modifier
                    .padding(start = 2.dp)
                    .align(Alignment.BottomStart),
        )
    }
    HorizontalDivider(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(3.dp),
        // Ancho máximo de la línea
        thickness = 1.dp, // Grosor de la línea
        color = Color.Gray, // Color de la línea
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun CalendarPreview() {
    CalendarView()
}
