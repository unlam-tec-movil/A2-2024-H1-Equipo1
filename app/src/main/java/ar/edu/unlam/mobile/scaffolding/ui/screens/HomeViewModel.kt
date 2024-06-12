package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.model.Pet
import ar.edu.unlam.mobile.scaffolding.domain.services.PetService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
sealed interface PetListUIState {
    data class Success(
        val pets: List<PetViewData>,
    ) : PetListUIState

    data object Loading : PetListUIState

    data class Error(
        val message: String,
    ) : PetListUIState
}

data class PetViewData(
    val pet: Pet,
    var selected: MutableState<Boolean> = mutableStateOf(false),
)

fun PetViewData.isSelected(): Boolean = this.selected.value

fun PetViewData.toggleSelection() {
    this.selected.value = !this.selected.value
}

fun Pet.toPetViewData(isSelected: Boolean = false): PetViewData = PetViewData(this, mutableStateOf(isSelected))

data class HomeUIState(
    val petListUIState: PetListUIState,
    val petsToDelete: MutableList<Pet>,
    val isPetSelectionActivated: Boolean,
)

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val petService: PetService,
    ) : ViewModel() {
        // Mutable State Flow contiene un objeto de estado mutable. Simplifica la operación de
        // actualización de información y de manejo de estados de una aplicación: Cargando, Error, Éxito
        // (https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
        // _helloMessage State es el estado del componente "HelloMessage" inicializado como "Cargando"
        private val petListState = MutableStateFlow(PetListUIState.Loading)
        private val selectedPets: MutableList<PetViewData> = mutableListOf()

        // _Ui State es el estado general del view model.
        private val _uiState =
            MutableStateFlow(
                HomeUIState(
                    petListState.value,
                    mutableListOf(),
                    false,
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

        fun togglePetSelection() {
            _uiState.value =
                _uiState.value.copy(
                    isPetSelectionActivated = !_uiState.value.isPetSelectionActivated,
                )
            if (selectedPets.isNotEmpty()) {
                for (petView in selectedPets) {
                    petView.toggleSelection()
                }
                selectedPets.clear()
            }
        }

        fun selectPet(petViewData: PetViewData) {
            petViewData.toggleSelection()
            if (petViewData.isSelected()) {
                selectedPets.add(petViewData)
            } else {
                selectedPets.remove(petViewData)
            }
        }

        fun deletePets() {
            viewModelScope.launch {
                selectedPets.forEach {
                    petService.deletePet(it.pet)
                }
                fetchPets()
            }
            togglePetSelection()
        }

        suspend fun fetchPets() {
            petService
                .getPetList()
                .collect {
                    _uiState.value =
                        _uiState.value.copy(
                            petListUIState =
                                PetListUIState.Success(
                                    it.map { pet ->
                                        pet.toPetViewData()
                                    },
                                ),
                        )
                }
        }
    }
