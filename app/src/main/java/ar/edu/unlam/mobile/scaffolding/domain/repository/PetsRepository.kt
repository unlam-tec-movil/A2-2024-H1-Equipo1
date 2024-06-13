package ar.edu.unlam.mobile.scaffolding.domain.repository

import ar.edu.unlam.mobile.scaffolding.domain.model.Pet
import kotlinx.coroutines.flow.Flow

interface PetsRepository {
    fun getPetList(): Flow<List<Pet>>

    suspend fun getPetById(id: Int): Pet?

    suspend fun deletePet(pet: Pet)

    suspend fun addPet(pet: Pet)
}
