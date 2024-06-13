package ar.edu.unlam.mobile.scaffolding.data.di

import android.content.Context
import androidx.room.Room
import ar.edu.unlam.mobile.scaffolding.data.local.PetDatabase
import ar.edu.unlam.mobile.scaffolding.data.repository.PetsRepositoryImpl
import ar.edu.unlam.mobile.scaffolding.domain.repository.PetsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val PET_DATABASE_NAME = "pet_database"

    @Provides
    @Singleton
    fun provideRoom(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(context, PetDatabase::class.java, PET_DATABASE_NAME).build()

    @Provides
    @Singleton
    fun providePetRepository(db: PetDatabase): PetsRepository {
        return PetsRepositoryImpl(db.getPetDao())
    }
}
