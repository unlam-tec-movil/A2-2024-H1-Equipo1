package ar.edu.unlam.mobile.scaffolding.domain.services

import ar.edu.unlam.mobile.scaffolding.domain.model.Pet
import ar.edu.unlam.mobile.scaffolding.domain.repository.PetsRepository
import ar.edu.unlam.mobile.scaffolding.domain.usecases.PetsUseCases
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PetService
    @Inject
    constructor(private val repo: PetsRepository) : PetsUseCases {
        override fun getPetList(): Flow<List<Pet>> {
            return repo.getPetList()
        }

        override fun deletePet(pet: Pet): Flow<Pet> {
            return repo.deletePet(pet)
        }
    }
