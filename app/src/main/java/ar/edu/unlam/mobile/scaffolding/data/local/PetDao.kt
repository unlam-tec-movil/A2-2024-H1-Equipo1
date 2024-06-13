package ar.edu.unlam.mobile.scaffolding.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ar.edu.unlam.mobile.scaffolding.domain.model.Pet
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pet: Pet)

    @Delete
    suspend fun delete(pet: Pet)

    @Query("SELECT * from pet WHERE id = :id")
    suspend fun getPetById(id: Int): Pet?

    @Query("SELECT * from pet ORDER BY id ASC")
    fun getPets(): Flow<List<Pet>>
}
