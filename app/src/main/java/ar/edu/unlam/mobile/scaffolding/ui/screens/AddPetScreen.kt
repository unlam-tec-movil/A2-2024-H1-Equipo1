package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.ui.components.BiographyTextBox
import ar.edu.unlam.mobile.scaffolding.ui.components.PetInfoField
import ar.edu.unlam.mobile.scaffolding.ui.components.PurpleButton

@Composable
fun AddPetScreen(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier =
                modifier
                    .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PetInfoField(
                title = "Nombre: ",
                value = "Pepe",
                onValueChange = {},
            )
            PetInfoField(
                title = "Edad: ",
                value = "30",
                onValueChange = {},
            )
            PetInfoField(
                title = "Peso: ",
                value = "30",
                onValueChange = {},
            )
            PetInfoField(
                title = "Tipo: ",
                value = "perro",
                onValueChange = {},
            )
            BiographyTextBox(
                value = "Biografiaaaa",
                onValueChange = {},
            )
        }
        PurpleButton(
            title = "Guardar",
            action = {},
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
