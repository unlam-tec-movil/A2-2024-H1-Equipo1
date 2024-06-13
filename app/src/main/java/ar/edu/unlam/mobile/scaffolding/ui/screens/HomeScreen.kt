package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.AddButton
import ar.edu.unlam.mobile.scaffolding.ui.components.CustomText
import ar.edu.unlam.mobile.scaffolding.ui.components.DeleteButton
import ar.edu.unlam.mobile.scaffolding.ui.components.PetCard
import ar.edu.unlam.mobile.scaffolding.ui.components.SelectCircle

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    onAddButtonClick: () -> Unit,
    onDetailPetButtonClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState: HomeUIState by viewModel.uiState.collectAsState()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            if (uiState.isPetSelectionActivated) {
                DeleteButton(onClick = { viewModel.deletePets() })
            } else {
                AddButton(action = onAddButtonClick)
            }
        },
    ) { paddingValues ->
        // La información que obtenemos desde el view model la consumimos a través de un estado de
        // "tres vías": Loading, Success y Error. Esto nos permite mostrar un estado de carga,
        // un estado de éxito y un mensaje de error.

        when (val list = uiState.petListUIState) {
            is PetListUIState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }

            is PetListUIState.Success -> {
                LaunchedEffect(Unit) {
                    viewModel.fetchPets()
                }
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(top = paddingValues.calculateTopPadding()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (list.pets.isEmpty()) {
                        CustomText(
                            text = "Comenzá a agregar mascotas!",
                            fontSize = 48.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 40.sp,
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            items(list.pets) { petViewData ->
                                Row(
                                    modifier =
                                        Modifier
                                            .combinedClickable(
                                                onClick = {
                                                    if (uiState.isPetSelectionActivated) {
                                                        viewModel.selectPet(petViewData)
                                                    } else {
                                                        onDetailPetButtonClick(petViewData.pet.id)
                                                    }
                                                },
                                                onLongClick = {
                                                    viewModel.togglePetSelection()
                                                    viewModel.selectPet(petViewData)
                                                },
                                            ),
                                ) {
                                    AnimatedVisibility(
                                        visible = uiState.isPetSelectionActivated,
                                        modifier = Modifier.align(Alignment.CenterVertically),
                                    ) {
                                        SelectCircle(
                                            isPetSelected =
                                                petViewData.isSelected(),
                                            onClick = {
                                                viewModel.selectPet(petViewData)
                                            },
                                        )
                                    }
                                    PetCard(
                                        petViewData.pet,
                                    )
                                }
                            }
                        }
                    }
                }
            }

            is PetListUIState.Error -> {
                // Error
            }
        }
    }
    BackHandler(
        enabled = uiState.isPetSelectionActivated,
        onBack = { viewModel.togglePetSelection() },
    )
}
