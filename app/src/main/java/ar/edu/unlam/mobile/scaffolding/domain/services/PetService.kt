package ar.edu.unlam.mobile.scaffolding.domain.services

import ar.edu.unlam.mobile.scaffolding.domain.model.Pet
import ar.edu.unlam.mobile.scaffolding.domain.repository.PetsRepository
import ar.edu.unlam.mobile.scaffolding.domain.usecases.PetUseCases
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PetService
    @Inject
    constructor(private val petsRepository: PetsRepository) : PetUseCases {
        override fun getPetList(): Flow<List<Pet>> {
            return petsRepository.getPetList()
        }

        override fun deletePet(pet: Pet) {
            petsRepository.deletePet(pet)
        }

        override fun addPet(pet: Pet) {
            petsRepository.addPet(pet)
        }
    }
