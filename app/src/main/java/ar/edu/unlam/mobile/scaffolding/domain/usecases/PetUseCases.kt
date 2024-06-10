package ar.edu.unlam.mobile.scaffolding.domain.usecases

import ar.edu.unlam.mobile.scaffolding.domain.model.Pet
import kotlinx.coroutines.flow.Flow

interface PetUseCases {
    fun getPetList(): Flow<List<Pet>>

    fun deletePet(pet: Pet)

    fun addPet(pet: Pet)
}
