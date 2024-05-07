package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.ui.theme.Purple1

@Composable
fun AddButton(action: () -> Unit) {
    IconButton(onClick = action,
        modifier = Modifier
            .size(width = 60.dp, height = 60.dp)) {
        Box {
            Icon(
                Icons.Default.Add,
                contentDescription = null,
                tint = Purple1,
                modifier = Modifier
                    .fillMaxSize()
            )

            Icon(
                Icons.Rounded.AddCircle,
                contentDescription = null,
                tint = Color(0xFFD9D9D9),
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewMas(){
    AddButton(action = { })
}