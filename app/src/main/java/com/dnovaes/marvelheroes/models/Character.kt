package com.dnovaes.marvelheroes.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Transient

@Entity
data class Character(
    var id: Int = 0,
    @Id(assignable = true) var _id: Long = id.toLong(),
    val name: String,
    val description: String,
    @Transient val thumbnail: CharacterThumbnail
)
