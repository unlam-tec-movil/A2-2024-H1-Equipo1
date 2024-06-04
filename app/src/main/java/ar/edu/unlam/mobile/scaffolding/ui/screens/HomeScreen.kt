package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ar.edu.unlam.mobile.scaffolding.ui.components.AddButton
import ar.edu.unlam.mobile.scaffolding.ui.components.CustomText
import ar.edu.unlam.mobile.scaffolding.ui.components.DeleteButton
import ar.edu.unlam.mobile.scaffolding.ui.components.InteractiveList

@Composable
fun HomeScreen(
    onAddButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState: HomeUIState by viewModel.uiState.collectAsStateWithLifecycle(
        lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current,
    )
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            if (uiState.isPetSelectionActivated.value) {
                DeleteButton(onClick = viewModel::deletePets)
            } else {
                AddButton(action = onAddButtonClick)
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) { paddingValues ->
        // La información que obtenemos desde el view model la consumimos a través de un estado de
        // "tres vías": Loading, Success y Error. Esto nos permite mostrar un estado de carga,
        // un estado de éxito y un mensaje de error.

        when (val petList = uiState.petListUIState) {
            PetListUIState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is PetListUIState.Success -> {
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(top = paddingValues.calculateTopPadding()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (petList.pets.isEmpty()) {
                        CustomText(
                            text = "Comenzá a agregar mascotas!",
                            fontSize = 48.sp,
                        )
                    } else {
                        InteractiveList(
                            items = petList.pets,
                            onClick = viewModel::selectPet,
                            onLongClick = viewModel::toggleSelectionMode,
                            showAction = uiState.isPetSelectionActivated.value,
                        )
                    }
                }
            }

            is PetListUIState.Error -> {
                LaunchedEffect(snackBarHostState) {
                    snackBarHostState.showSnackbar(
                        message = petList.message,
                        actionLabel = "OK",
                    )
                }
            }
        }
    }
    BackHandler(
        enabled = uiState.isPetSelectionActivated.value,
        onBack = viewModel::toggleSelectionMode,
    )
}
