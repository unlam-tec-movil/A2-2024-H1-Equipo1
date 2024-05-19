package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.AddButton
import ar.edu.unlam.mobile.scaffolding.ui.components.CustomText
import ar.edu.unlam.mobile.scaffolding.ui.components.PetCard

@Composable
fun HomeScreen(
    onAddButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            AddButton(action = onAddButtonClick)
        },
    ) { paddingValues ->
        // La información que obtenemos desde el view model la consumimos a través de un estado de
        // "tres vías": Loading, Success y Error. Esto nos permite mostrar un estado de carga,
        // un estado de éxito y un mensaje de error.
        val uiState: HomeUIState by viewModel.uiState.collectAsState()

        when (val helloState = uiState.helloMessageState) {
            is HelloMessageUIState.Loading -> {
                // Loading
            }

            is HelloMessageUIState.Success -> {
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(top = paddingValues.calculateTopPadding()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (uiState.currentPets.isEmpty()) {
                        CustomText(
                            text = "Comenzá a agregar mascotas!",
                            fontSize = 48.sp,
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            items(uiState.currentPets) { pet ->
                                PetCard(
                                    pet,
                                    onClick = { },
                                )
                            }
                        }
                    }
                }
            }

            is HelloMessageUIState.Error -> {
                // Error
            }
        }
    }
}
