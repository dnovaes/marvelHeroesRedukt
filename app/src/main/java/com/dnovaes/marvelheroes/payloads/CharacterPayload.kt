package com.dnovaes.marvelheroes.payloads


data class CharacterPayload(
    val offset: Int,
    val searchFilter: String = ""
)