package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.local.pets
import ar.edu.unlam.mobile.scaffolding.domain.model.Pet
import ar.edu.unlam.mobile.scaffolding.domain.repository.PetsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PetsMockRepository
    @Inject
    constructor() : PetsRepository {
        private val petList: Flow<List<Pet>> =
            flow {
                emit(pets)
            }

        override fun getPetList(): Flow<List<Pet>> {
            return petList
        }

        override suspend fun getPetById(id: Int): Pet? {
            TODO("Not yet implemented")
        }

        override suspend fun deletePet(pet: Pet) {
            pets.forEach {
                if (it.name == pet.name) {
                    pets.remove(it)
                }
            }
        }

        override suspend fun addPet(pet: Pet) {
            pets.add(pet)
        }
    }
