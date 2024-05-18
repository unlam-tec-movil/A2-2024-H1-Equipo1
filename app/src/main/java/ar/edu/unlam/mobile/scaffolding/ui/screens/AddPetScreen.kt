package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.unlam.mobile.scaffolding.ui.components.PetInfoField

@Composable
fun AddPetScreen(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PetInfoField(
            title = "Nombre: ",
            value = "Pepe",
            onValueChange = {},
        )
    }
}

@Preview(
    showSystemUi = true,
)
@Composable
private fun AddScreenPreview() {
    AddPetScreen({})
}
