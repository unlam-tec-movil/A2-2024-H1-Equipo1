package ar.edu.unlam.mobile.scaffolding.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pet")
data class Pet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val bio: String,
    val age: Int,
    val weight: Float,
    val foodConsumed: Float = 0f,
    val waterConsumer: Float = 0f,
    val distanceWalked: Float = 0f,
)
