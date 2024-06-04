package ar.edu.unlam.mobile.scaffolding.domain.repository

import ar.edu.unlam.mobile.scaffolding.domain.model.Pet
import kotlinx.coroutines.flow.Flow

interface PetsRepository {
    fun getPetList(): Flow<List<Pet>>

    fun deletePet(pet: Pet): Flow<Pet>
}
