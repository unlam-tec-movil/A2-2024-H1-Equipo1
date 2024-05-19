package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.data.local.pets
import ar.edu.unlam.mobile.scaffolding.domain.model.Pet
import ar.edu.unlam.mobile.scaffolding.domain.model.PetType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class AddPetState(
    val name: String = "",
    val age: String = "",
    val weight: String = "",
    val type: String = "",
    val bio: String = "",
)

@HiltViewModel
class AddPetViewModel
    @Inject
    constructor() : ViewModel() {
        private val _state = MutableStateFlow(AddPetState())
        val state = _state.asStateFlow()

        fun savePet(context: Context): Boolean {
            if (checkPetData(context)) {
                val type =
                    if (state.value.type == "perro") {
                        PetType.DOG
                    } else {
                        PetType.CAT
                    }
                pets.add(
                    Pet(
                        name = _state.value.name,
                        age = _state.value.age.toInt(),
                        weight = _state.value.weight.toFloat(),
                        bio = _state.value.bio,
                        type = type,
                    ),
                )
                return true
            }
            return false
        }

        fun updateName(value: String) {
            _state.value =
                _state.value.copy(
                    name = value,
                )
        }

        fun updateAge(value: String) {
            _state.value =
                _state.value.copy(
                    age = value,
                )
        }

        fun updateWeight(value: String) {
            _state.value =
                _state.value.copy(
                    weight = value,
                )
        }

        fun updateType(value: String) {
            _state.value =
                _state.value.copy(
                    type = value,
                )
        }

        fun updateBio(value: String) {
            _state.value =
                _state.value.copy(
                    bio = value,
                )
        }

        private fun checkPetData(context: Context): Boolean {
            if (_state.value.name.isEmpty()) {
                showToast(context, "Ingresá un Nombre!")
                return false
            }
            if (_state.value.age.isEmpty()) {
                showToast(context, "Ingresá la edad!")
                return false
            }
            if (_state.value.weight.isEmpty()) {
                showToast(context, "Ingresá el peso!")
                return false
            }
            if (_state.value.type.isEmpty()) {
                showToast(context, "Ingresá el tipo!")
                return false
            }
            return true
        }

        private fun showToast(
            context: Context,
            text: String,
        ) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }
