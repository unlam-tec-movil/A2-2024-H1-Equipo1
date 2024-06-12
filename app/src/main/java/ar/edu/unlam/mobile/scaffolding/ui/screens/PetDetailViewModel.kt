package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.local.pets
import ar.edu.unlam.mobile.scaffolding.domain.model.Pet
import ar.edu.unlam.mobile.scaffolding.domain.model.PetType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class PetDetailViewModel
    @Inject
    constructor() : ViewModel() {
        private val _petName = MutableStateFlow("")
        private val _foodConsumed = MutableStateFlow(0f)
        private val _waterConsumed = MutableStateFlow(0f)
        private val _distanceWalked = MutableStateFlow(0f)
        private val _recommendedRations = MutableStateFlow(0)
        private val _recommendedWaterRations = MutableStateFlow(0)

        val petName: StateFlow<String> get() = _petName
        val foodConsumed: StateFlow<Float> get() = _foodConsumed
        val waterConsumed: StateFlow<Float> get() = _waterConsumed
        val distanceWalked: StateFlow<Float> get() = _distanceWalked
        val recommendedRations: StateFlow<Int> get() = _recommendedRations
        val recommendedWaterRations: StateFlow<Int> get() = _recommendedWaterRations

        fun updateFoodConsumed(food: Int) {
            _foodConsumed.value += food
        }

        fun updateWaterConsumed(water: Int) {
            _waterConsumed.value += water
        }

        fun updateDistanceWalked(distance: Float) {
            _distanceWalked.value += distance
        }

        fun loadPetDetails(petId: Int) {
            viewModelScope.launch {
                // Simular la carga de datos para la mascota
                val pet = getPetById(petId)
                _petName.value = pet.name
                _recommendedRations.value = calculateRecommendedRations(pet.weight)
                _recommendedWaterRations.value = calculateRecommendedRations(pet.weight)
                resetDailyValuesAtMidnight()
            }
        }

        private fun getPetById(petId: Int): Pet {
            // Simular obtención de datos
            return pets.firstOrNull { it.id == petId } ?: Pet(
                petId,
                "Desconocido",
                "",
                3,
                PetType.DOG,
                2F,
            )
        }

        private fun calculateRecommendedRations(weight: Float): Int =
            when {
                weight in 1f..10f -> 1 // Pequeños: 1 ración
                weight in 11f..25f -> 2 // Medianos: 2 raciones
                weight in 26f..45f -> 3 // Grandes: 3 raciones
                weight > 45f -> 4 // Gigantes: 4 raciones
                else -> 0
            }

        private fun calculateRecommendedWaterRations(weight: Float): Int =
            when {
                weight in 1f..10f -> 3 // Pequeños: 3 raciones de agua al dia
                weight in 11f..25f -> 4 // Medianos: 4
                weight in 26f..45f -> 6 // Grandes: 6 raciones
                weight > 45f -> 8 // Gigantes: 8 raciones
                else -> 0
            }

        private fun resetDailyValuesAtMidnight() {
            val now = Calendar.getInstance()
            val nextMidnight =
                Calendar.getInstance().apply {
                    add(Calendar.DAY_OF_YEAR, 1)
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                }

            val delay = nextMidnight.timeInMillis - now.timeInMillis
            viewModelScope.launch {
                kotlinx.coroutines.delay(delay)
                _foodConsumed.value = 0f
                _waterConsumed.value = 0f
                _distanceWalked.value = 0f
                resetDailyValuesAtMidnight()
            }
        }
    }
