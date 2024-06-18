package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.model.Pet
import ar.edu.unlam.mobile.scaffolding.domain.services.PetService
import ar.edu.unlam.mobile.scaffolding.ui.theme.WeatherCould
import ar.edu.unlam.mobile.scaffolding.ui.theme.WeatherCouldCool
import ar.edu.unlam.mobile.scaffolding.ui.theme.WeatherHot
import ar.edu.unlam.mobile.scaffolding.ui.theme.WeatherIdeal
import ar.edu.unlam.mobile.scaffolding.ui.theme.WeatherRain
import ar.edu.unlam.mobile.scaffolding.ui.theme.WeatherStorm
import ar.edu.unlam.mobile.scaffolding.ui.theme.WeatherSunny
import ar.edu.unlam.mobile.scaffolding.ui.theme.WeatherSunnyCool
import com.mikepenz.iconics.typeface.library.weathericons.WeatherIcons
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
                // Crea una copia de la lista para evitar ConcurrentModificationException
                val petsToDelete = selectedPets.toList()
                petsToDelete.forEach {
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

        fun getWeatherMessage(
            temperature: Int,
            condition: String,
            humidity: Int,
            windSpeed: Double,
        ): String =
            when {
                temperature > 30 && condition.contains("Despejado") && humidity < 50 ->
                    "Evitá pasear entre las 11AM y las 5PM. \nHidratá a tus mascotas."

                temperature > 30 && condition.contains("Nublado") ->
                    "Hace calor, pero no está tan agresivo el sol. \nRealizá un paseo corto y llevá agua."

                temperature in 20..30 && condition.contains("Despejado") && windSpeed < 10 ->
                    "¡Es un día ideal para pasear!"

                temperature in 20..30 && condition.contains("Nublado") ->
                    "Realizá un paseo corto, puede llegar a llover."

                temperature in 10..20 && condition.contains("Despejado") ->
                    "Hace frío, pero podés pasear un rato. \nEstá despejado."

                temperature in 10..20 && condition.contains("Nublado") ->
                    "Hace frío y está nublado. \nIntentá evitar los paseos largos."

                condition.contains("Lluvia") || condition.contains("Nieve") ->
                    "Recomendamos no pasear ahora. \nPosponé el paseo por un rato."

                condition.contains("Tormenta") ->
                    "No salgas y mantené a tus mascotas dentro."

                windSpeed > 30 ->
                    "Vientos fuertes detectados. \nEvitá pasear para evitar accidentes."

                else ->
                    "Condiciones climáticas no reconocidas. \nProcede con precaución."
            }

        @Composable
        fun getWeatherEmoji(keyWeather: String): WeatherIcons.Icon {
            val weatherIcon =
                when {
                    keyWeather.contains("Despejado") -> WeatherIcons.Icon.wic_day_sunny

                    keyWeather.contains("Nublado") -> WeatherIcons.Icon.wic_day_cloudy

                    keyWeather.contains("Lluvia") -> WeatherIcons.Icon.wic_day_rain

                    keyWeather.contains("Nieve") -> WeatherIcons.Icon.wic_day_snow

                    keyWeather.contains("Tormenta") -> WeatherIcons.Icon.wic_day_storm_showers

                    keyWeather.contains("Ventoso") -> WeatherIcons.Icon.wic_day_windy

                    else ->
                        WeatherIcons.Icon.wic_alien
                }
            return weatherIcon
        }

        @Composable
        fun getMessageBackgroundColor(message: String): Color =
            when {
                message.contains("Evitá pasear entre las 11AM y las 5PM") -> WeatherHot
                message.contains("Hace calor, pero no está tan agresivo el sol. Realizá un paseo corto y llevá agua.")
                -> WeatherSunny

                message.contains("¡Es un día ideal para pasear!") -> WeatherIdeal
                message.contains("Realizá un paseo corto, puede llegar a llover.") -> WeatherCould
                message.contains("Hace frío, pero podés pasear un rato. Está despejado.") -> WeatherSunnyCool
                message.contains("Hace frío y está nublado. Intentá evitar los paseos largos.") -> WeatherCouldCool
                message.contains("Recomendamos no pasear ahora. Posponé el paseo por un rato.") -> WeatherRain
                message.contains("No salgas y mantené a tus mascotas dentro.") -> WeatherStorm
                message.contains("Vientos fuertes detectados. Evitá pasear para evitar accidentes.") -> Color.Magenta
                else -> Color.Gray
            }
    }
