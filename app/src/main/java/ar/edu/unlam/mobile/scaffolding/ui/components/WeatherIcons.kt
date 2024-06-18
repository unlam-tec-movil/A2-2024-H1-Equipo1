package ar.edu.unlam.mobile.scaffolding.ui.components

import android.widget.ImageView
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.weathericons.WeatherIcons
import com.mikepenz.iconics.utils.sizeDp

@Composable
fun WeatherIcon(icon: WeatherIcons.Icon) {
    val context = LocalContext.current
    val weatherIconDrawable =
        IconicsDrawable(context, icon).apply {
            sizeDp = 64
        }

    AndroidView(
        factory = { context ->
            ImageView(context).apply {
                setImageDrawable(weatherIconDrawable)
            }
        },
        modifier = Modifier.size(64.dp),
    )
}
