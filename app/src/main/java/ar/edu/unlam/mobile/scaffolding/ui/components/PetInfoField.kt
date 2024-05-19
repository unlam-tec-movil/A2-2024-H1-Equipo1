package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.ui.theme.LightGray

@Composable
fun PetInfoField(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit,
    fontSize: TextUnit = 32.sp,
    textColor: Color = Color.Black,
    backGroundColor: Color = LightGray,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .height(86.dp)
                .background(
                    shape = RoundedCornerShape(8.dp),
                    color = backGroundColor,
                ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        CustomText(
            text = title,
            maxLines = 1,
            fontSize = fontSize,
            color = textColor,
        )
        Spacer(modifier = Modifier.width(8.dp))
        TextField(
            value = value,
            onValueChange = { onValueChange(it) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            textStyle =
                TextStyle.Default.copy(
                    color = textColor,
                    fontSize = fontSize,
                    fontFamily = FontFamily(Font(R.font.anni_use_your_telescope)),
                ),
            colors =
                TextFieldDefaults.colors().copy(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                ),
        )
    }
}

@Preview(
    showSystemUi = true,
)
@Composable
private fun PetInfoFieldPreview() {
    PetInfoField(
        title = "Nombre:",
        value = "Roco",
        onValueChange = { },
    )
}
