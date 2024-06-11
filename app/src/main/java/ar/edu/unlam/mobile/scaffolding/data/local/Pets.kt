package ar.edu.unlam.mobile.scaffolding.data.local

import ar.edu.unlam.mobile.scaffolding.domain.model.Pet
import ar.edu.unlam.mobile.scaffolding.domain.model.PetType

val pets: MutableList<Pet> =
    mutableListOf(
        Pet(
            name = "Roco",
            age = 4,
            type = PetType.DOG,
            weight = 32.2f,
            bio = "",
        ),
        Pet(
            name = "Peter",
            age = 4,
            type = PetType.DOG,
            weight = 32.2f,
            bio = "",
        ),
        Pet(
            name = "Luna",
            age = 4,
            type = PetType.CAT,
            weight = 32.2f,
            bio = "",
        ),
    )
