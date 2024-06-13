package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.local.PetDao
import ar.edu.unlam.mobile.scaffolding.domain.model.Pet
import ar.edu.unlam.mobile.scaffolding.domain.repository.PetsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PetsRepositoryImpl
    @Inject
    constructor(private val petDao: PetDao) : PetsRepository {
        override fun getPetList(): Flow<List<Pet>> = petDao.getPets()

        override suspend fun getPetById(id: Int): Pet? = petDao.getPetById(id)

        override suspend fun deletePet(pet: Pet) = petDao.delete(pet)

        override suspend fun addPet(pet: Pet) = petDao.insert(pet)
    }
