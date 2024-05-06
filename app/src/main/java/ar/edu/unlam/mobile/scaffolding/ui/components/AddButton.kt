package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddButton() {
    Text(text = "+",
        modifier = Modifier
            .padding(10.dp)
            .drawBehind {
                drawCircle(
                    color = Color.Magenta,
                    radius = 30f
                )
            }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMas(){
    AddButton()
}