package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.BiographyTextBox
import ar.edu.unlam.mobile.scaffolding.ui.components.PetInfoField
import ar.edu.unlam.mobile.scaffolding.ui.components.PurpleButton

@Composable
fun AddPetScreen(
    onSaveButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddPetViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
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
                value = state.name,
                onValueChange = { viewModel.updateName(it) },
            )
            PetInfoField(
                title = "Edad: ",
                value = state.age,
                onValueChange = { viewModel.updateAge(it) },
                keyboardType = KeyboardType.Number,
            )
            PetInfoField(
                title = "Peso: ",
                value = state.weight,
                onValueChange = { viewModel.updateWeight(it) },
                keyboardType = KeyboardType.Decimal,
            )
            PetInfoField(
                title = "Tipo: ",
                value = state.type,
                onValueChange = { viewModel.updateType(it) },
            )
            BiographyTextBox(
                value = state.bio,
                onValueChange = { viewModel.updateBio(it) },
            )
        }
        PurpleButton(
            title = "Guardar",
            action = {
                if (viewModel.savePet(context)) {
                    onSaveButtonClick()
                }
            },
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
