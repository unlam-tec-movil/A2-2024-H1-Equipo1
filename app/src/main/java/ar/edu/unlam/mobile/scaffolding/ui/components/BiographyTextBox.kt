package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.ui.theme.LightGray

@Composable
fun BiographyTextBox(
    value: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 32.sp,
    textColor: Color = Color.Black,
    backGroundColor: Color = LightGray,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier =
            modifier
                .height(300.dp)
                .background(
                    color = backGroundColor,
                    shape = RoundedCornerShape(8.dp),
                ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CustomText(
            text = "Biografía",
            color = textColor,
            fontSize = fontSize,
        )
        BasicTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            textStyle =
                TextStyle.Default.copy(
                    color = textColor,
                    fontSize = fontSize,
                    fontFamily = FontFamily(Font(R.font.anni_use_your_telescope)),
                ),
            modifier =
                Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
        )
    }
}

@Preview(
    showSystemUi = true,
)
@Composable
private fun BioPreview() {
    BiographyTextBox(value = "") {
    }
}
