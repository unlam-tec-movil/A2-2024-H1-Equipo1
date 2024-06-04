package ar.edu.unlam.mobile.scaffolding.domain.usecases

import ar.edu.unlam.mobile.scaffolding.domain.model.Pet
import kotlinx.coroutines.flow.Flow

interface PetsUseCases {
    fun getPetList(): Flow<List<Pet>>

    fun deletePet(pet: Pet): Flow<Pet>
}
