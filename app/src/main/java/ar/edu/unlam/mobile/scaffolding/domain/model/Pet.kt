package ar.edu.unlam.mobile.scaffolding.domain.model

data class Pet(
    val id: Int,
    val name: String,
    val bio: String,
    val age: Int,
    val type: PetType,
    val weight: Float,
)
