package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.model.Pet
import ar.edu.unlam.mobile.scaffolding.domain.usecases.PetsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
sealed interface PetListUIState {
    data class Success(val pets: List<PetViewData>) : PetListUIState

    data object Loading : PetListUIState

    data class Error(val message: String) : PetListUIState
}

data class PetViewData(
    val pet: Pet,
    var selected: MutableState<Boolean> = mutableStateOf(false),
)

fun PetViewData.isSelected(): Boolean {
    return this.selected.value
}

fun PetViewData.toggleSelection() {
    this.selected.value = !this.selected.value
}

fun Pet.toPetViewData(isSelected: Boolean = false): PetViewData {
    return PetViewData(this, mutableStateOf(isSelected))
}

data class HomeUIState(
    val petListUIState: PetListUIState,
    val isPetSelectionActivated: MutableState<Boolean>,
)

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val petService: PetsUseCases,
    ) : ViewModel() {
        // Mutable State Flow contiene un objeto de estado mutable. Simplifica la operación de
        // actualización de información y de manejo de estados de una aplicación: Cargando, Error, Éxito
        // (https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)

        private val selectedPets: MutableList<PetViewData> = mutableListOf()

        private val petListState = MutableStateFlow(PetListUIState.Loading)

        // _Ui State es el estado general del view model.
        private val _uiState =
            MutableStateFlow(
                HomeUIState(
                    petListState.value,
                    mutableStateOf(false),
                ),
            )

        // UIState expone el estado anterior como un Flujo de Estado de solo lectura.
        // Esto impide que se pueda modificar el estado desde fuera del ViewModel.
        val uiState = _uiState.asStateFlow()

        init {
            viewModelScope.launch {
                fetchPets()
            }
        }

        fun toggleSelectionMode() {
            _uiState.value.isPetSelectionActivated.value = !_uiState.value.isPetSelectionActivated.value
        }

        fun selectPet(pet: PetViewData) {
            pet.toggleSelection()
            selectedPets.add(pet)
        }

        fun deletePets() {
            viewModelScope.launch {
                selectedPets.forEach { petView ->
                    if (petView.isSelected()) {
                        petService.deletePet(petView.pet)
                            .catch {
                                _uiState.value =
                                    _uiState.value.copy(
                                        petListUIState = PetListUIState.Error(it.message ?: "error desconocido al borrar un pet"),
                                    )
                            }.collect()
                    }
                }
                fetchPets()
            }
        }

        private suspend fun fetchPets() {
            petService.getPetList()
                .catch {
                    _uiState.value = _uiState.value.copy(petListUIState = PetListUIState.Error(it.message ?: "error desconocido"))
                }
                .collect {
                    _uiState.value =
                        _uiState.value.copy(
                            petListUIState =
                                PetListUIState.Success(
                                    it.map { pet -> pet.toPetViewData() },
                                ),
                        )
                }
        }
    }
