package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.ui.theme.Purple2


@Composable
fun PurpleButton(title: String, action: () -> Unit) {
    Button(
        onClick = action,
        modifier = Modifier
            .height(53.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Purple2)

    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewPurpleButton(){
    PurpleButton (title = "Guardar"){
        
    }
    
}