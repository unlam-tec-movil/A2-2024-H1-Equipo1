package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.R

@Composable
fun CustomText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 12.sp,
    textAlign: TextAlign? = null,
    color: Color = Color.Black,
    maxLines: Int = Int.MAX_VALUE,
    lineHeight: TextUnit = TextUnit.Unspecified,
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize,
        fontFamily = FontFamily(Font(R.font.anni_use_your_telescope)),
        textAlign = textAlign,
        color = color,
        maxLines = maxLines,
        lineHeight = lineHeight,
    )
}
