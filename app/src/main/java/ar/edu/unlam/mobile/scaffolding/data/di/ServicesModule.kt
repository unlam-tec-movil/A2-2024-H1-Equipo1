package ar.edu.unlam.mobile.scaffolding.data.di

import ar.edu.unlam.mobile.scaffolding.domain.services.PetService
import ar.edu.unlam.mobile.scaffolding.domain.usecases.PetUseCases
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServicesModule {
    // @Binds
    // abstract fun bindPetsRepository(petsRepositoryImpl: PetsRepositoryImpl): PetsRepository

    @Binds
    abstract fun bindPetsService(petsService: PetService): PetUseCases
}
