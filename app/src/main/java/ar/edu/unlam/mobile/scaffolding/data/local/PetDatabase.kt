package ar.edu.unlam.mobile.scaffolding.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.edu.unlam.mobile.scaffolding.domain.model.Pet

@Database(entities = [Pet::class], version = 1)
abstract class PetDatabase : RoomDatabase() {
    abstract fun getPetDao(): PetDao
}
